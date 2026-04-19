package com.adl.service.repository;

import com.adl.service.data.LoginData;
import com.adl.service.http.request.LoginRequest;
import com.adl.service.http.request.LogoutRequest;
import com.adl.service.repository.fetch.LoginAuthFetchHelper;
import com.adl.service.repository.persist.LoginAuthPersistHelper;
import com.adl.service.repository.prepare.LoginAuthPrepareHelper;

import io.reactivex.rxjava3.core.Single;

public final class LoginAuthRepository {
    private final LoginAuthPrepareHelper prepareHelper = new LoginAuthPrepareHelper();
    private final LoginAuthFetchHelper fetchHelper = new LoginAuthFetchHelper();
    private final LoginAuthPersistHelper persistHelper = new LoginAuthPersistHelper();

    public LoginAuthRepository() {
    }

    public Single<LoginData> login(LoginRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createLoginFunc());
    }

    public Single<String> logout(LogoutRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createLogoutFunc());
    }
}
