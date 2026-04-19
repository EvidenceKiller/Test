package com.adl.service.repository.persist;

import android.text.TextUtils;

import com.adl.service.data.BasePageData;
import com.adl.service.data.SceneData;
import com.adl.service.data.SportSkuData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.SceneEntity;
import com.adl.service.db.entity.SportSkuEntity;
import com.adl.service.exception.NzCommonException;
import com.adl.service.exception.NzEmptyDataException;
import com.adl.service.http.request.SceneListRequest;
import com.adl.service.http.request.SportSkuPageRequest;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.functions.Function;

public final class OperationPersistHelper {

    public OperationPersistHelper() {
    }

    public Function<List<SceneData>, List<SceneData>> createPersistSceneListFunc(SceneListRequest request) {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                throw new NzEmptyDataException("scene list is empty");
            }
            if (TextUtils.isEmpty(request.getAppCode())) {
                DaoManagerProxy.getInstance().getSceneDao().clearAll();
            } else {
                DaoManagerProxy.getInstance().getSceneDao().clearByAppCode(request.getAppCode());
            }
            List<SceneEntity> entityList = dataList.stream()
                    .map(data -> SceneEntity.convertToEntity(data, request.getAppCode()))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getSceneDao().insertAll(entityList);
            return dataList;
        };
    }

    public Function<BasePageData<SportSkuData>, BasePageData<SportSkuData>> createPersistSportSkuPageFunc(SportSkuPageRequest request) {
        return (pageData) -> {
            if (pageData == null || pageData.getRecords() == null || pageData.getRecords().isEmpty()) {
                throw new NzCommonException("sport sku page data is empty");
            }
            List<SportSkuEntity> entityList = pageData.getRecords().stream()
                    .map(data -> SportSkuEntity.convertToEntity(data, request.getAppCode(), request.getSceneId()))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getSceneSportDao().insertAll(entityList);
            return pageData;
        };
    }
}
