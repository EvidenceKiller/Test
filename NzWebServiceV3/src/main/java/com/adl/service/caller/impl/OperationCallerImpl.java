package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.OperationCaller;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.log.NzLog;
import com.adl.service.persistence.BasePagePersistence;
import com.adl.service.persistence.BasePersistence;
import com.adl.service.persistence.OperationPersistenceHelper;
import com.adl.service.utils.InnerUtil;
import com.adl.service.http.request.BannerDetailListRequest;
import com.adl.service.http.request.DeviceFocusListRequest;
import com.adl.service.http.request.DictMapRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.SceneListRequest;
import com.adl.service.http.request.SportSkuDetailRequest;
import com.adl.service.http.request.SportSkuPageRequest;
import com.adl.service.http.request.UploadDeviceNameRequest;
import com.adl.service.data.BannerDetailData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.DeviceFocusData;
import com.adl.service.data.DictMapData;
import com.adl.service.callback.GetPageResult;
import com.adl.service.data.SceneData;
import com.adl.service.data.SportSkuData;
import com.adl.service.data.SportSkuDetailData;
import com.adl.service.web.OperationService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * OperationCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class OperationCallerImpl implements OperationCaller {

    private final OperationService operationService;

    public OperationCallerImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void reLoadAllSceneDataSync(Map<String, List<String>> map) throws NzBaseException {
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
            List<SceneData> sceneList = getSceneListSync(request)
                    .stream()
                    .filter(sceneData -> sceneIds.contains(sceneData.getId()))
                    .collect(Collectors.toList());

            ////////////// 2.同步场景下运动列表 ////////////
            for (SceneData sceneData : sceneList) {
                SportSkuPageRequest req = SportSkuPageRequest.builder()
                        .appCode(appCode)
                        .sceneId(sceneData.getId())
                        .build();
                getSportSkuPagesAllSync(req);
            }
        }
    }

    @Override
    public long getBannerDetailListAsync(RequestScope scope, BannerDetailListRequest request, RequestCallback<List<BannerDetailData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getBannerDetailList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<BannerDetailData> getBannerDetailListSync(BannerDetailListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(operationService.getBannerDetailList(request));
    }

    @Override
    public long getSportSkuPagesAllAsync(RequestScope scope, SportSkuPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(BasePagePersistence.persistAllPagesAsSingle(
                OperationPersistenceHelper.createSportSkuPagePreparer(),
                OperationPersistenceHelper.createSportSkuPageRequester(request, req -> operationService.getSportSkuPage(req)),
                OperationPersistenceHelper.createSportSkuPagePersister(request.getAppCode(), request.getSceneId())), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getSportSkuPagesAllSync(SportSkuPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(BasePagePersistence.persistAllPagesAsSingle(
                OperationPersistenceHelper.createSportSkuPagePreparer(),
                OperationPersistenceHelper.createSportSkuPageRequester(request, req -> operationService.getSportSkuPage(req)),
                OperationPersistenceHelper.createSportSkuPagePersister(request.getAppCode(), request.getSceneId())));
    }

    @Override
    public long getSportSkuPageAsync(RequestScope scope, SportSkuPageRequest request, RequestCallback<BasePageData<SportSkuData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(request, req -> operationService.getSportSkuPage(req)),
                OperationPersistenceHelper.createSportSkuPagePersister(request.getAppCode(), request.getSceneId())), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<SportSkuData> getSportSkuPageSync(SportSkuPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(request, req -> operationService.getSportSkuPage(req)),
                OperationPersistenceHelper.createSportSkuPagePersister(request.getAppCode(), request.getSceneId())));
    }

    @Override
    public long getSportSkuDetailAsync(RequestScope scope, SportSkuDetailRequest request, RequestCallback<SportSkuDetailData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getSportSkuDetail(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public SportSkuDetailData getSportSkuDetailSync(SportSkuDetailRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(operationService.getSportSkuDetail(request));
    }

    @Override
    public long getDictMapAsync(RequestScope scope, DictMapRequest request, RequestCallback<List<DictMapData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getDictMap(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DictMapData> getDictMapSync(DictMapRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(operationService.getDictMap(request));
    }

    @Override
    public long getSceneListAsync(RequestScope scope, SceneListRequest request, RequestCallback<List<SceneData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePersistence.persistAsSingle(
                OperationPersistenceHelper.createSceneListPreparer(request.getAppCode()),
                BasePersistence.createBaseRequester(request, req -> operationService.getSceneList(req)),
                OperationPersistenceHelper.createSceneListPersister(request.getAppCode())), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SceneData> getSceneListSync(SceneListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePersistence.persistAsSingle(
                OperationPersistenceHelper.createSceneListPreparer(request.getAppCode()),
                BasePersistence.createBaseRequester(request, req -> operationService.getSceneList(req)),
                OperationPersistenceHelper.createSceneListPersister(request.getAppCode())));
    }

    @Override
    public long uploadDeviceNameAsync(RequestScope scope, UploadDeviceNameRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.uploadDeviceName(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean uploadDeviceNameSync(UploadDeviceNameRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(operationService.uploadDeviceName(request));
    }

    @Override
    public long getDeviceFocusListAsync(RequestScope scope, DeviceFocusListRequest request, RequestCallback<List<DeviceFocusData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getDeviceFocusList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DeviceFocusData> getDeviceFocusListSync(DeviceFocusListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(operationService.getDeviceFocusList(request));
    }
}
