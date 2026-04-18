package com.adl.service.http.interceptor;

import com.adl.auth.core.AdlAuthFactory;
import com.adl.service.AdlService;
import com.adl.service.utils.CommonUtil;
import com.adl.service.log.NzLog;
import com.ssp.oss.sdk.core.OssClient;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 全局自动刷新Token的拦截器
 */
public class TokenRefreshInterceptor implements Interceptor {

    private int autoLoginFailNum = 0;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        NzLog.d("response.code = " + response.code());

        if (isTokenExpired(response)) {
            autoLoginFailNum = 0;
            // 根据和服务端的约定判断token过期
            NzLog.d("静默自动刷新Token,然后重新请求数据");

            // 同步请求方式，获取最新的Token
            String newSession = autoLogin();

            // 使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer " + newSession)
                    .header("Accept-Language", CommonUtil.getLocaleLanguage())
                    .build();
            // 重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    //  自动登录
    private String autoLogin() {
        if (autoLoginFailNum > 10) {
            NzLog.d("自动登录失败");
            return "";
        }

        Map<String, String> result = AdlAuthFactory.create(AdlService.getService().getContext()).deviceLogin();
        if (result != null && "0".equals(result.get("code"))) {
            String accessToken = AdlAuthFactory.create(AdlService.getService().getContext()).getAccessToken();
            AdlService.getService().setDeviceToken(accessToken);
            //更新OSS的Token
            OssClient.getInstance().updateAuthorization("Bearer " + accessToken);
            return accessToken;
        } else {
            autoLoginFailNum++;
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return autoLogin();
        }
    }

    //  根据Response，判断Token是否失效
    private boolean isTokenExpired(Response response) {
        return response.code() == 401;
    }

}