package com.adl.service.web;

import android.text.TextUtils;

import com.adl.service.common.BaseService;
import com.adl.service.common.FileDownCallback;
import com.adl.service.common.FileDownManager;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.http.interceptor.SmartCacheInterceptor;
import com.adl.service.http.interceptor.TokenInjectionInterceptor;
import com.adl.service.http.interceptor.TokenRefreshInterceptor;
import com.adl.service.outer.AdlService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/1
 * Describe   : web 服务
 */
public class BaseHttpService extends BaseService {

    // json 类型
    protected static MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    private static volatile OkHttpClient HTTP_CLIENT;

    private static long httpCacheTime = 30 * 24 * 60 * 60; //接口缓存时间

    protected BaseHttpService() {
    }

    protected static OkHttpClient getHttpClient() {
        if (HTTP_CLIENT == null) {
            synchronized (BaseHttpService.class) {
                if (HTTP_CLIENT == null) {
                    HTTP_CLIENT = createOkHttpClient();
                }
            }
        }
        return HTTP_CLIENT;
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.retryOnConnectionFailure(false);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(new TokenInjectionInterceptor());
        builder.addInterceptor(new TokenRefreshInterceptor());

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> d("SPORT_HTTP -> message:" + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(new SmartCacheInterceptor(AdlService.getService().getContext(), true, httpCacheTime));
        return builder.build();
    }


    protected static String wrapperUrl(String path) {
        return getConfigService() != null ? (getConfigService().getHost() + path) : "";
    }

    // 下载公共文件，通过 Get 可直接访问
    public static boolean downloadCommonFile(FileDownManager.FileDownTask bean,
                                             FileDownCallback callback) {
        try {
            String url = bean.url;
            Request request = new Request.Builder().url(url).build();
            Response response = getHttpClient().newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                ResponseBody respBody = response.body();
                MediaType mediaType = respBody.contentType();
                if (mediaType == null) return false;

                long length = respBody.contentLength();
                InputStream input = respBody.byteStream();

                File file = bean.localFile;
                bean.contentLength = length;

                // 文件是否存在
                if (file.exists()) {
                    file.delete();
                }

                OutputStream out = new FileOutputStream(file);
                long totalRead = 0;
                int read = -1;
                byte[] buffer = new byte[64 * 1024];
                while ((read = input.read(buffer)) > 0) {
                    out.write(buffer, 0, read);
                    out.flush();
                    totalRead += read;
                    if (callback != null) {
                        callback.onProgress(length, totalRead);
                    }
                }
                InnerUtil.close(out);
                InnerUtil.close(input);
                closeResponseBody(respBody);

                // 是否读完
                if (totalRead != length) {
                    file.delete();
                    if (callback != null) {
                        callback.onError("文件下载失败");
                    }
                    return false;
                }

                d("文件下载完成");

                return true;
            } else {
                if (response.body() != null) {
                    String content = response.body().string();
                    callback.onError(content);
                }
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.onError(e.getMessage());
            }
        }

        return false;
    }

    protected static void closeResponseBody(ResponseBody body) {
        try {
            if (body != null) {
                body.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 当前系统时间
    public static long currentSystemTime() {
        try {
            String ipHost = getIpHost();

            Request request = new Request.Builder().url(ipHost + "/time").build();
            Response response = getHttpClient().newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String content = response.body().string();
                JSONObject jo = JSON.parseObject(content);
                // 服务器返回时间为秒
                return jo.getLongValue("timestamp") * 1000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO 需要考虑本地时间异常情况
        return 0L;
    }

    public static String getIpHost() {
        String hostUrl = getConfigService() != null ? getConfigService().getHost() : "";

        String regex = "https?://(?:www\\.)?(?:[\\w-]+\\.)+[\\w-]+(:\\d+)?";
        Matcher matcher = Pattern.compile(regex).matcher(hostUrl);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "https://www.nezhasport.com";
    }

    // 获取七牛云token
    protected static String getQiNiuToken(String bucket) {
        try {
            InnerPreferences pf = InnerPreferences.instance();
            final String keyTime = bucket + LastQiNiuTime;
            final String keyToken = bucket + LastQiNiuToken;
            long lastTime = pf.readLong(keyTime);
            String token = pf.readString(keyToken);
            // 30 分钟过期
            if (System.currentTimeMillis() - lastTime < 5 * IDefine.Minute) {
                if (!TextUtils.isEmpty(token)) {
                    return token;
                }
            }

            //  七牛云token地址
            String url = "";
            if (getConfigService() != null) {
                url = getConfigService().getQiNiuHost() + "/uc/oss/v1/qiniu/token";
            }

            //  参数
            Map<String, Object> map = new HashMap<>();
            map.put("bucket", bucket);
            String param = InnerUtil.toJson(map);

            //  post 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            Response response = getHttpClient().newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String content = response.body().string();
                //  去除双引号
                token = content.replaceAll("\"", "");
                pf.putString(keyToken, token);
                pf.putLong(keyTime, System.currentTimeMillis());
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 解析Response
    static RequestResult parserResponse(Response response) {
        try {
            if (response.isSuccessful() && response.body() != null) {
                String str = response.body().string();
                JSONObject jo = JSON.parseObject(str);
                int code = jo.getIntValue("code");
                if (code == 200) {
                    String data = jo.getString("data");
                    return RequestResult.okContent(data);
                } else {
                    String msg = jo.getString("msg");
                    e("警告: 接口内部错误, msg=" + msg);
                    return RequestResult.error(code, msg);
                }
            } else {
                String error = "服务器内部错误!!!";
                if (response.body() != null) {
                    error = response.body().string();
                }
                e("警告: 请求返回错误," + error);
                return RequestResult.error("服务器内部错误!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.error(e.toString());
        }
    }

    static RequestResult parserPageResponse(Response response) {
        try {
            if (response.isSuccessful() && response.body() != null) {
                String str = response.body().string();
                JSONObject jo = JSON.parseObject(str);
                int code = jo.getIntValue("code");
                if (code == 200) {
                    JSONObject obj = jo.getJSONObject("data");
                    String data = obj.getString("records");
                    int current = obj.getIntValue("current");
                    int pages = obj.getIntValue("pages");
                    int size = obj.getIntValue("size");
                    int total = obj.getIntValue("total");
                    d("分页数据: current=" + current + ", pages=" + pages + ", size=" + size + ", total=" + total);
                    return RequestResult.okContent(data);
                } else {
                    return RequestResult.error(code, jo.getString("msg"));
                }
            } else {
                if (response.body() != null) {
                    return RequestResult.error(response.body().string());
                }
                return RequestResult.error("服务器内部错误!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResult.error(e.toString());
        }
    }
}
