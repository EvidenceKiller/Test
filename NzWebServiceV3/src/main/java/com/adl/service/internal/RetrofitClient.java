package com.adl.service.internal;

import com.adl.service.http.interceptor.HeaderInterceptor;
import com.adl.service.http.interceptor.TokenRefreshInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit + OkHttp 单例客户端（SDK 内部使用，不对外暴露）
 */
public final class RetrofitClient {

    private boolean isInit = false;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public RetrofitClient() {
    }

    public synchronized void init(String baseUrl, boolean debug) {
        if (isInit) {
            return;
        }
        // 1. OkHttpClient
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new TokenRefreshInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(debug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
//                .addInterceptor(new SmartCacheInterceptor(AdlService.getService().getContext(), true, HTTP_CACHE_TIME))
                .build();

        // 2. Retrofit（支持 RxJava3）
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        isInit = true;
    }

    /**
     * 重置单例（用于切换 baseUrl，如环境切换）
     */
    public synchronized void release() {
        if (!isInit) {
            return;
        }
        // 关闭 OkHttpClient
        if (okHttpClient != null) {
            // 关闭连接池
            okHttpClient.connectionPool().evictAll();

            // 关闭缓存（如果有）
            if (okHttpClient.cache() != null) {
                try {
                    okHttpClient.cache().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 取消所有正在进行的请求
            okHttpClient.dispatcher().cancelAll();

            okHttpClient = null;
        }
        retrofit = null;
        isInit = false;
    }

    public <T> T create(Class<T> service) {
        if (!isInit) {
            throw new IllegalStateException("请先调用 init() 初始化");
        }
        return retrofit.create(service);
    }
}
