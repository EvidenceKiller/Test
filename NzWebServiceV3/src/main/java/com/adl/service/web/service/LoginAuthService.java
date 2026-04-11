package com.adl.service.web.service;

import com.adl.service.web.request.LoginRequest;
import com.adl.service.web.request.LogoutRequest;
import com.adl.service.web.response.BaseResponse;
import com.adl.service.web.response.LoginData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API（v3new 生成）
 */
public interface LoginAuthService {
    /**
     * 设备登录
     */
    @POST("sso/login")
    Single<BaseResponse<LoginData>> login(@Body LoginRequest request);

    /**
     * 退出登录
     */
    @POST("sso/logout")
    Single<BaseResponse<String>> logout(@Body LogoutRequest request);
}
