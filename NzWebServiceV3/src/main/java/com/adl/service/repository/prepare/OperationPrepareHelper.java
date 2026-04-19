package com.adl.service.repository.prepare;

import android.text.TextUtils;

import com.adl.service.data.SceneData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.exception.NzCommonException;
import com.adl.service.http.request.SceneListRequest;
import com.adl.service.http.request.SportSkuPageRequest;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Function;

public final class OperationPrepareHelper {

    public OperationPrepareHelper() {
    }

    public Function<SceneListRequest, List<SceneData>> createGetSceneListPrepareFunc() {
        return (request) -> {
            if (request == null) {
                throw new NzCommonException("request is null when getStudentPagesAll");
            }
            if (request.getForceUpdate()) {
                // TODO 强制更新
                return null;
            }
            return null;
        };
    }

    public static Function<SportSkuPageRequest, Void> createGetSportSkuPagesAllFunc() {
        return (requestu) -> {
            DaoManagerProxy.getInstance().getSceneSportDao().clearAll();
            return null;
        };
    }
}
