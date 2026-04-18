package com.adl.service.http.interceptor;

import android.text.TextUtils;

import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.log.NzLog;
import com.adl.service.utils.CommonUtil;

import java.io.IOException;
import java.util.UUID;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
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
            reqBuilder.addHeader("Platform", "TERMINAL");
            reqBuilder.addHeader("RequestId", UUID.randomUUID().toString());
            reqBuilder.addHeader("Timestamp", String.valueOf(System.currentTimeMillis()));
//            reqBuilder.addHeader("Authorization", "Bearer " + token);
            reqBuilder.addHeader("Authorization", "Bearer j8rreQ5w6oVBt3MbzLrYfUPAlUfpvbVMrLYzccjYaT_ZS50gpvFFfS5h_V7EKYTIHid-BBNGwsimOkshyc-9ERI4Ymf345EbqfDXNkAr2gagDMuIqqid_-5Vlj4Q4nlt");
//            reqBuilder.addHeader("Authorization", "Bearer XB2r4uugDrxaU0KhfKTvIRxuPPhS9cIoxE38xQ3-g_W8YlUoYcXROt_9-Cdc9ftwBexz__zLAfDmGCvG3tnBm1gp---601aLTg3bg7JJ6Y2B7V-cn1clEZ0ZDAUTYVVo");
            reqBuilder.addHeader("Accept-Language", CommonUtil.getLocaleLanguage());
            // next
            return chain.proceed(reqBuilder.build());
        } else {
            return chain.proceed(request);
        }
    }
}
