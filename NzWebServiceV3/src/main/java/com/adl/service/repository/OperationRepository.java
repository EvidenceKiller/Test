package com.adl.service.repository;

import com.adl.service.callback.GetPageResult;
import com.adl.service.data.BannerDetailData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.DeviceFocusData;
import com.adl.service.data.DictMapData;
import com.adl.service.data.SceneData;
import com.adl.service.data.SportSkuData;
import com.adl.service.data.SportSkuDetailData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.BannerDetailListRequest;
import com.adl.service.http.request.DeviceFocusListRequest;
import com.adl.service.http.request.DictMapRequest;
import com.adl.service.http.request.SceneListRequest;
import com.adl.service.http.request.SportMeetPageRequest;
import com.adl.service.http.request.SportSkuDetailRequest;
import com.adl.service.http.request.SportSkuPageRequest;
import com.adl.service.http.request.UploadDeviceNameRequest;
import com.adl.service.log.NzLog;
import com.adl.service.repository.fetch.OperationFetchHelper;
import com.adl.service.repository.persist.OperationPersistHelper;
import com.adl.service.repository.prepare.OperationPrepareHelper;
import com.adl.service.utils.InnerUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Single;

public final class OperationRepository {
    private final OperationPrepareHelper prepareHelper = new OperationPrepareHelper();
    private final OperationFetchHelper fetchHelper = new OperationFetchHelper();
    private final OperationPersistHelper persistHelper = new OperationPersistHelper();

    public OperationRepository() {
    }

    public Single<Void> reLoadAllSceneData(Map<String, List<String>> map) throws NzBaseException {
        long serverTime = InnerUtil.getServerTime();
        NzLog.d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));

        DaoManagerProxy.getInstance().getSceneDao().clearAll();
        DaoManagerProxy.getInstance().getSceneSportDao().clearAll();

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String appCode = entry.getKey();
            List<String> sceneIds = entry.getValue();
            ////////////// 1.同步场景列表 ////////////
            SceneListRequest request = SceneListRequest.builder()
                    .appCode(appCode)
                    .build();
            List<SceneData> sceneList = getSceneList(request).blockingGet()
                    .stream()
                    .filter(sceneData -> sceneIds.contains(sceneData.getId()))
                    .collect(Collectors.toList());

            ////////////// 2.同步场景下运动列表 ////////////
            for (SceneData sceneData : sceneList) {
                SportSkuPageRequest req = SportSkuPageRequest.builder()
                        .appCode(appCode)
                        .sceneId(sceneData.getId())
                        .build();
                getSportSkuPagesAll(req).blockingGet();
            }
        }
        return null;
    }

    public Single<List<BannerDetailData>> getBannerDetailList(BannerDetailListRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetBannerDetailListFunc());
    }

    public Single<GetPageResult> getSportSkuPagesAll(SportSkuPageRequest request) {
        return BaseRepositoryUtil.repositoryPagesAsSingle(request,
                prepareHelper.createGetSportSkuPagesAllFunc(),
                fetchHelper.createGetSportSkuPagesAllFunc(request),
                persistHelper.createPersistSportSkuPageFunc(request));
    }

    public Single<BasePageData<SportSkuData>> getSportSkuPage(SportSkuPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                fetchHelper.createGetSportSkuPageFunc(),
                persistHelper.createPersistSportSkuPageFunc(request));
    }

    public Single<SportSkuDetailData> getSportSkuDetail(SportSkuDetailRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetSportSkuDetailFunc());
    }

    public Single<List<DictMapData>> getDictMap(DictMapRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetDictMapFunc());
    }

    public Single<List<SceneData>> getSceneList(SceneListRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                prepareHelper.createGetSceneListPrepareFunc(),
                fetchHelper.createGetSceneListFunc(),
                persistHelper.createPersistSceneListFunc(request));
    }

    public Single<Boolean> uploadDeviceName(UploadDeviceNameRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createUploadDeviceNameFunc());
    }

    public Single<List<DeviceFocusData>> getDeviceFocusList(DeviceFocusListRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetDeviceFocusListFunc());
    }
}
