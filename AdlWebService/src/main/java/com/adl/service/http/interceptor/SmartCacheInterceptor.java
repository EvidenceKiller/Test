package com.adl.service.http.interceptor;

import android.content.Context;
import android.util.Log;

import com.adl.service.common.InnerLogger;
import com.adl.service.entity.CacheEntry;
import com.adl.service.http.cache.CacheEventListener;
import com.adl.service.http.cache.HttpFileCache;
import com.adl.service.http.cache.IHttpCache;
import com.adl.service.utils.HttpCacheUtil;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SmartCacheInterceptor implements Interceptor {

    private final Context context;
    private final boolean enableCache;
    private final long cacheTimeSeconds;
    //接口缓存白名单
    private final List<String> cachePathWhitelist = Arrays.asList(
            "banner/v1/detail/list",
            "statistics/sport/sportRankKing",
            "statistics/sport/exerciseSumTimeRank",
            "statistics/sport/physicalTrainingRank",
            "sportSku/v1/getSportSkuList",
            "sportSku/v1/getSportSkuDetail",
            "statistics/sport/sportSkuRank",
            "org/res/v1/list",
            "org/res/v1/list/type",
            "cs/wiki/v1/getWikiList",
            "cs/wiki/type/v1/getWikiTypeList",
            "grade/v1/getGradeTree",
            "organizeTest/getPlanList",
            "organizeTest/getPlanClassList",
            "organizeTest/checkStudent",
            "organizeTest/planInfo"
    );
    private IHttpCache customCache;
    private CacheEventListener cacheEventListener;

    public SmartCacheInterceptor(Context context,
                                 boolean enableCache,
                                 long cacheTimeSeconds) {
        this.context = context;
        this.enableCache = enableCache;
        this.cacheTimeSeconds = cacheTimeSeconds;
        this.customCache = new HttpFileCache(context);
    }

    public void setCacheEventListener(CacheEventListener listener) {
        this.cacheEventListener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        // 判断是否需要缓存
        if (!shouldCacheRequest(originalRequest)) {
            return chain.proceed(originalRequest);
        }

        String cacheKey = generateCacheKey(originalRequest);

        try {
            // 尝试网络请求
            Response response = proceedWithTimeout(chain, originalRequest);

            if (response.isSuccessful()) {
                // 网络请求成功，更新缓存
                cacheResponse(response, cacheKey);
                return response;
            } else {
                // HTTP状态码错误（非200），尝试返回缓存
                return handleHttpError(response, cacheKey, originalRequest);
            }
        } catch (Exception e) {
            // 网络异常（超时、连接失败等），返回缓存
            return handleNetworkError(e, cacheKey, originalRequest);
        }
    }

    private boolean shouldCacheRequest(Request request) {
        if (!enableCache) {
            return false;
        }
        String url = request.url().toString();
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        // 检查白名单（如果白名单不为空，则只在白名单内缓存）
        if (!cachePathWhitelist.isEmpty()) {
            for (String whitePath : cachePathWhitelist) {
                //用endsWith判断
                if (url.endsWith(whitePath)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String generateCacheKey(Request request) {
        // 基于URL、请求体和请求头生成唯一缓存key
        String url = request.url().toString();
        // 包含请求体（如果有）
        String bodyStr = "";
        RequestBody body = request.body();
        if (body != null) {
            try {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                bodyStr = buffer.readUtf8();
            } catch (Exception e) {
                // 忽略错误
            }
        }
        Log.d("generateCacheKey", "url: " + url + "--" + bodyStr);
        return HttpCacheUtil.getCacheKey(url, bodyStr);
    }

    private Response proceedWithTimeout(Chain chain, Request request) throws IOException {
        return chain
                .withConnectTimeout(10, TimeUnit.SECONDS)
                .withReadTimeout(15, TimeUnit.SECONDS)
                .withWriteTimeout(15, TimeUnit.SECONDS)
                .proceed(request);
    }

    private void cacheResponse(Response response, String cacheKey) {
        try {
            // 克隆响应体，因为响应体只能被读取一次
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                return;
            }

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer().clone();

            String responseBodyString = buffer.readUtf8();

            // 创建缓存条目
            CacheEntry cacheEntry = new CacheEntry(
                    responseBodyString,
                    System.currentTimeMillis(),
                    System.currentTimeMillis() + (cacheTimeSeconds * 1000),
                    response.headers().toMultimap(),
                    response.code(),
                    response.request().url().toString()
            );

            // 保存到缓存
            customCache.save(cacheKey, cacheEntry);

            // 记录缓存统计
            notifyCacheEvent("CACHE_SAVED", cacheKey, true,
                    "Size: " + responseBodyString.length() + " bytes");
        } catch (Exception e) {
            notifyCacheEvent("CACHE_SAVE_ERROR", cacheKey, false, e.getMessage());
        }
    }

    private Response handleHttpError(Response response,
                                     String cacheKey,
                                     Request originalRequest) {
        notifyCacheEvent("HTTP_ERROR", cacheKey, false,
                "Status: " + response.code());
        CacheEntry cacheEntry = customCache.get(cacheKey);
        InnerLogger.logger().e("handleHttpError code: " + response.code() + ",message: " + response.message());
        if (cacheEntry != null && !cacheEntry.isExpired()) {
            // 返回缓存数据
            notifyCacheEvent("CACHE_HIT", cacheKey, true,
                    "HTTP error, returning cache");
            return buildCachedResponse(originalRequest, cacheEntry);
        } else {
            // 没有有效缓存，或缓存过期，返回原始错误响应,并删除过期数据
            customCache.remove(cacheKey);
            notifyCacheEvent("CACHE_MISS", cacheKey, false,
                    "No valid cache for HTTP error");
            return response;
        }
    }

    private Response handleNetworkError(Exception e,
                                        String cacheKey,
                                        Request originalRequest) throws IOException {
        String errorType;
        if (e instanceof java.net.SocketTimeoutException) {
            errorType = "TIMEOUT";
        } else if (e instanceof java.net.ConnectException) {
            errorType = "CONNECT_ERROR";
        } else if (e instanceof java.net.UnknownHostException) {
            errorType = "NO_NETWORK";
        } else {
            errorType = "NETWORK_ERROR";
        }
        CacheEntry cacheEntry = customCache.get(cacheKey);
        notifyCacheEvent(errorType, cacheKey, false, e.getMessage());

        InnerLogger.logger().e("handleNetworkError errorType: " + errorType + ",message: " + e.getMessage());

        if (cacheEntry != null && !cacheEntry.isExpired()) {
            // 返回缓存数据
            notifyCacheEvent("CACHE_HIT", cacheKey, true,
                    errorType + ", returning cache");
            return buildCachedResponse(originalRequest, cacheEntry);
        } else {
            // 没有有效缓存，抛出异常
            notifyCacheEvent("CACHE_MISS", cacheKey, false,
                    errorType + ", no valid cache");
            customCache.remove(cacheKey);
            if (e instanceof IOException) {
                throw (IOException) e;
            } else {
                throw new IOException(e);
            }
        }
    }

    private Response buildCachedResponse(Request request, CacheEntry cacheEntry) {
        // 构建缓存响应
        ResponseBody responseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                cacheEntry.getData()
        );

        Response.Builder builder = new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(cacheEntry.getStatusCode())
                .message("OK (from cache)")
                .body(responseBody)
                .addHeader("X-Cache-Source", "local-cache")
                .addHeader("X-Cache-Time", String.valueOf(cacheEntry.getTimestamp()))
                .addHeader("X-Cache-Expire", String.valueOf(cacheEntry.getExpireTime()))
                .addHeader("X-Cache-Age", String.valueOf(cacheEntry.getAgeInSeconds()));

        // 添加原始头部
        Map<String, List<String>> headers = cacheEntry.getHeaders();
        if (headers != null) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                for (String value : entry.getValue()) {
                    builder.addHeader(entry.getKey(), value);
                }
            }
        }
        InnerLogger.logger().d("网络请求出错：命中缓存，返回缓存数据: " + cacheEntry.getUrl());
        return builder.build();
    }

    private void notifyCacheEvent(String event, String key, boolean success, String message) {
        // 输出日志
        Log.d("SmartCache",
                String.format("Event: %s, Key: %s, Success: %s, Message: %s",
                        event, key, success, message));

        // 通知监听器
        if (cacheEventListener != null) {
            String url = "";
            try {
                // 从key中解析URL（简化处理）
                if (key != null && key.contains(":")) {
                    String[] parts = key.split(":");
                    if (parts.length > 1) {
                        url = parts[1];
                    }
                }
            } catch (Exception e) {
                // 忽略解析错误
            }

            if ("CACHE_HIT".equals(event)) {
                cacheEventListener.onCacheHit(url, 0);
            } else if ("CACHE_MISS".equals(event)) {
                cacheEventListener.onCacheMiss(url);
            } else if ("CACHE_SAVED".equals(event)) {
                cacheEventListener.onCacheSaved(url, message != null ? message.length() : 0);
            } else if (event.contains("ERROR")) {
                cacheEventListener.onCacheError(url, message != null ? message : event);
            }
        }
    }

}
