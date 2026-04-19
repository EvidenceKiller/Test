package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.GetPageResult;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.OperationCaller;
import com.adl.service.data.BannerDetailData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.DeviceFocusData;
import com.adl.service.data.DictMapData;
import com.adl.service.data.SceneData;
import com.adl.service.data.SportSkuData;
import com.adl.service.data.SportSkuDetailData;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.BannerDetailListRequest;
import com.adl.service.http.request.DeviceFocusListRequest;
import com.adl.service.http.request.DictMapRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.SceneListRequest;
import com.adl.service.http.request.SportSkuDetailRequest;
import com.adl.service.http.request.SportSkuPageRequest;
import com.adl.service.http.request.UploadDeviceNameRequest;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.repository.OperationRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * OperationCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class OperationCallerImpl implements OperationCaller {

    private final OperationRepository operationRepository;

    public OperationCallerImpl() {
        this.operationRepository = new OperationRepository();
    }

    @Override
    public void reLoadAllSceneDataSync(Map<String, List<String>> map) throws NzBaseException {
        RxCallbackScheduler.blockingGet(operationRepository.reLoadAllSceneData(map));
    }

    @Override
    public long getBannerDetailListAsync(RequestScope scope, BannerDetailListRequest request, RequestCallback<List<BannerDetailData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.getBannerDetailList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<BannerDetailData> getBannerDetailListSync(BannerDetailListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(operationRepository.getBannerDetailList(request));
    }

    @Override
    public long getSportSkuPagesAllAsync(RequestScope scope, SportSkuPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.getSportSkuPagesAll(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getSportSkuPagesAllSync(SportSkuPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(operationRepository.getSportSkuPagesAll(request));
    }

    @Override
    public long getSportSkuPageAsync(RequestScope scope, SportSkuPageRequest request, RequestCallback<BasePageData<SportSkuData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.getSportSkuPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<SportSkuData> getSportSkuPageSync(SportSkuPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(operationRepository.getSportSkuPage(request));
    }

    @Override
    public long getSportSkuDetailAsync(RequestScope scope, SportSkuDetailRequest request, RequestCallback<SportSkuDetailData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.getSportSkuDetail(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public SportSkuDetailData getSportSkuDetailSync(SportSkuDetailRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(operationRepository.getSportSkuDetail(request));
    }

    @Override
    public long getDictMapAsync(RequestScope scope, DictMapRequest request, RequestCallback<List<DictMapData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.getDictMap(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DictMapData> getDictMapSync(DictMapRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(operationRepository.getDictMap(request));
    }

    @Override
    public long getSceneListAsync(RequestScope scope, SceneListRequest request, RequestCallback<List<SceneData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.getSceneList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SceneData> getSceneListSync(SceneListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(operationRepository.getSceneList(request));
    }

    @Override
    public long uploadDeviceNameAsync(RequestScope scope, UploadDeviceNameRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.uploadDeviceName(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean uploadDeviceNameSync(UploadDeviceNameRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(operationRepository.uploadDeviceName(request));
    }

    @Override
    public long getDeviceFocusListAsync(RequestScope scope, DeviceFocusListRequest request, RequestCallback<List<DeviceFocusData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(operationRepository.getDeviceFocusList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DeviceFocusData> getDeviceFocusListSync(DeviceFocusListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(operationRepository.getDeviceFocusList(request));
    }
}
