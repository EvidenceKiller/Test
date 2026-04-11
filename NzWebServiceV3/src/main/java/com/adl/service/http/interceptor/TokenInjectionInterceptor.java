package com.adl.service.http.interceptor;

import android.text.TextUtils;

import com.adl.service.common.CommonUtil;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.log.NzLog;

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
        NzLog.d("执行请求: " + request);

        String token = InnerPreferences.instance().readString(IDefine.SpToken);

        if (!TextUtils.isEmpty(token)) {
            // 设置Token
            Request.Builder reqBuilder = request.newBuilder();
            // temp test token
//             reqBuilder.addHeader("Authorization", "Bearer F_WgWTj2KvExrO7FbVcUxZEK-mthkqWLw0a0L9syA_1zyjz5xdn03XkYeLkjzuZ9QzunoVRlEuFQ-hqf_uzYZu42v0qLdLFlKk0umRNOTzrBN6OOvAAk3vX7xApveY2F");
            reqBuilder.addHeader("Authorization", "Bearer j8rreQ5w6oVBt3MbzLrYfUPAlUfpvbVMrLYzccjYaT_ZS50gpvFFfS5h_V7EKYTIHid-BBNGwsimOkshyc-9ERI4Ymf345EbqfDXNkAr2gagDMuIqqid_-5Vlj4Q4nlt");
//            reqBuilder.addHeader("Authorization", "Bearer " + token);
            reqBuilder.addHeader("Accept-Language", CommonUtil.getLocaleLanguage());
            // next
            return chain.proceed(reqBuilder.build());
        } else {
            return chain.proceed(request);
        }
    }
}
