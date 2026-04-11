package com.adl.service.http.interceptor;

import android.text.TextUtils;

import com.adl.service.common.CommonUtil;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerLogger;
import com.adl.service.common.InnerPreferences;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInjectionInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        InnerLogger.logger().d("执行请求: " + request);

        String token = InnerPreferences.instance().readString(IDefine.SpToken);

        if (!TextUtils.isEmpty(token)) {
            // 设置Token
            Request.Builder reqBuilder = request.newBuilder();
            reqBuilder.addHeader("Authorization", "Bearer " + token);
            reqBuilder.addHeader("Accept-Language", CommonUtil.getLocaleLanguage());
            // next
            return chain.proceed(reqBuilder.build());
        } else {
            return chain.proceed(request);
        }
    }
}
