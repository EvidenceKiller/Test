package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.LoginRequest;
import com.adl.service.http.request.LogoutRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.data.LoginData;

/**
 * LoginAuthCaller。
 */
public interface LoginAuthCaller {

    /**
     * 设备登录
     * <p>异步调用。</p>
     */
    long loginAsync(RequestScope scope, LoginRequest request, RequestCallback<LoginData> callback);

    /**
     * 设备登录
     * <p>同步调用。</p>
     */
    LoginData loginSync(LoginRequest request) throws NzBaseException;

    /**
     * 退出登录
     * <p>异步调用。</p>
     */
    long logoutAsync(RequestScope scope, LogoutRequest request, RequestCallback<String> callback);

    /**
     * 退出登录
     * <p>同步调用。</p>
     */
    String logoutSync(LogoutRequest request) throws NzBaseException;

}
