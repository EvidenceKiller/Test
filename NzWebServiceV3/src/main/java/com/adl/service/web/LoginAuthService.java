package com.adl.service.web;

import com.adl.service.http.request.LoginRequest;
import com.adl.service.http.request.LogoutRequest;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.LoginData;

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
    BaseResponse<LoginData> login(@Body LoginRequest request);

    /**
     * 退出登录
     */
    @POST("sso/logout")
    BaseResponse<String> logout(@Body LogoutRequest request);
}
