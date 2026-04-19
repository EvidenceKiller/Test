package com.adl.service.repository.fetch;

import com.adl.service.data.LoginData;
import com.adl.service.http.request.LoginRequest;
import com.adl.service.http.request.LogoutRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.LoginAuthService;

import io.reactivex.rxjava3.functions.Function;

public final class LoginAuthFetchHelper {

    private final LoginAuthService loginAuthService;

    public LoginAuthFetchHelper() {
        this.loginAuthService = RetrofitManager.getInstance().create(LoginAuthService.class);
    }

    public Function<LoginRequest, LoginData> createLoginFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(loginAuthService.login(request));
    }

    public Function<LogoutRequest, String> createLogoutFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(loginAuthService.logout(request));
    }
}
