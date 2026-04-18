package com.adl.service.persistence;

import android.text.TextUtils;

import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.SceneEntity;
import com.adl.service.db.entity.SportSkuEntity;
import com.adl.service.exception.NzEmptyDataException;
import com.adl.service.http.request.SportSkuPageRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.SceneData;
import com.adl.service.data.SportSkuData;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public final class OperationPersistenceHelper {

    private OperationPersistenceHelper() {
    }

    public static Function<Void, Completable> createSportSkuPagePreparer() {
        return (v) -> {
            DaoManagerProxy.getInstance().getSceneSportDao().clearAll();
            return Completable.complete();
        };
    }

    public static Function<Long, Single<BaseResponse<BasePageData<SportSkuData>>>> createSportSkuPageRequester(
            SportSkuPageRequest request, Function<SportSkuPageRequest, Single<BaseResponse<BasePageData<SportSkuData>>>> function) {
        return (page) -> {
            SportSkuPageRequest pageReq = SportSkuPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .sportNature(request.getSportNature())
                    .sportType(request.getSportType())
                    .keyword(request.getKeyword())
                    .templateFlag(request.getTemplateFlag())
                    .ids(request.getIds())
                    .appCodes(request.getAppCodes())
                    .appCode(request.getAppCode())
                    .sceneId(request.getSceneId())
                    .delFlag(request.getDelFlag())
                    .enabled(request.getEnabled())
                    .build();
            return function.apply(pageReq);
        };
    }

    public static Function<List<SportSkuData>, List<SportSkuData>> createSportSkuPagePersister(String appCode, String sceneId) {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            List<SportSkuEntity> entityList = dataList.stream()
                    .map(data -> SportSkuEntity.convertToEntity(data, appCode, sceneId))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getSceneSportDao().insertAll(entityList);
            return dataList;
        };
    }

    public static Function<Void, Completable> createSceneListPreparer(String appCode) {
        return (v) -> {
            if (TextUtils.isEmpty(appCode)) {
                DaoManagerProxy.getInstance().getSceneDao().clearAll();
            } else {
                DaoManagerProxy.getInstance().getSceneDao().clearByAppCode(appCode);
            }
            return Completable.complete();
        };
    }

    public static Function<List<SceneData>, Completable> createSceneListPersister(String appCode) {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return Completable.error(new NzEmptyDataException("scene list is empty"));
            }
            List<SceneEntity> entityList = dataList.stream()
                    .map(data -> SceneEntity.convertToEntity(data, appCode))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getSceneDao().insertAll(entityList);
            return Completable.complete();
        };
    }
}
