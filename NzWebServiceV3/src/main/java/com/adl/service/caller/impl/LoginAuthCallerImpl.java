package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.LoginAuthCaller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.web.request.LoginRequest;
import com.adl.service.web.request.LogoutRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.response.LoginData;
import com.adl.service.web.service.LoginAuthService;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * LoginAuthCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class LoginAuthCallerImpl implements LoginAuthCaller {

    private final LoginAuthService loginAuthService;

    public LoginAuthCallerImpl(LoginAuthService loginAuthService) {
        this.loginAuthService = loginAuthService;
    }

    @Override
    public long loginAsync(RequestScope scope, LoginRequest request, RequestCallback<LoginData> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(loginAuthService.login(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public LoginData loginSync(LoginRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(loginAuthService.login(request));
    }

    @Override
    public long logoutAsync(RequestScope scope, LogoutRequest request, RequestCallback<String> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(loginAuthService.logout(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public String logoutSync(LogoutRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(loginAuthService.logout(request));
    }
}
