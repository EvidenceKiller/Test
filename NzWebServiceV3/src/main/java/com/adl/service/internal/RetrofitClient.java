package com.adl.service.internal;

import com.adl.service.AdlService;
import com.adl.service.http.interceptor.SmartCacheInterceptor;
import com.adl.service.http.interceptor.TokenInjectionInterceptor;
import com.adl.service.http.interceptor.TokenRefreshInterceptor;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit + OkHttp 单例客户端（SDK 内部使用，不对外暴露）
 */
public final class RetrofitClient {

    private static volatile RetrofitClient INSTANCE;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private static long HTTP_CACHE_TIME = 30 * 24 * 60 * 60; //接口缓存时间

    private RetrofitClient(String baseUrl, boolean debug) {
        // 1. OkHttpClient
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new TokenInjectionInterceptor())
                .addInterceptor(new TokenRefreshInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(debug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .addInterceptor(new SmartCacheInterceptor(AdlService.getService().getContext(), true, HTTP_CACHE_TIME))
                .build();

        // 2. Retrofit（支持 RxJava3）
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance(String baseUrl, boolean debug) {
        if (INSTANCE == null) {
            synchronized (RetrofitClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitClient(baseUrl, debug);
                }
            }
        }
        return INSTANCE;
    }

    /** 重置单例（用于切换 baseUrl，如环境切换） */
    public static void reset() {
        INSTANCE = null;
    }

    // TODO 临时
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
