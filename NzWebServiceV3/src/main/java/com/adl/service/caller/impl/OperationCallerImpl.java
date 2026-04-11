package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.OperationCaller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.web.request.BannerDetailListRequest;
import com.adl.service.web.request.DeviceFocusListRequest;
import com.adl.service.web.request.DictMapRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.SceneListRequest;
import com.adl.service.web.request.SportSkuDetailRequest;
import com.adl.service.web.request.SportSkuPageRequest;
import com.adl.service.web.request.UploadDeviceNameRequest;
import com.adl.service.web.request.WikiDetailRequest;
import com.adl.service.web.request.WikiPageRequest;
import com.adl.service.web.request.WikiTypePageRequest;
import com.adl.service.web.response.BannerDetailData;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.DeviceFocusData;
import com.adl.service.web.response.DictMapData;
import com.adl.service.web.response.SceneData;
import com.adl.service.web.response.SportSkuDetailData;
import com.adl.service.web.response.SportSkuData;
import com.adl.service.web.response.WikiData;
import com.adl.service.web.response.WikiDetailData;
import com.adl.service.web.response.WikiTypeData;
import com.adl.service.web.service.OperationService;

import java.util.List;

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
    public long getBannerDetailListAsync(RequestScope scope, BannerDetailListRequest request, RequestCallback<List<BannerDetailData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getBannerDetailList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<BannerDetailData> getBannerDetailListSync(BannerDetailListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getBannerDetailList(request));
    }

    @Override
    public long getSportSkuPageAsync(RequestScope scope, SportSkuPageRequest request, RequestCallback<BasePageData<SportSkuData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getSportSkuPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<SportSkuData> getSportSkuPageSync(SportSkuPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getSportSkuPage(request));
    }

    @Override
    public long getSportSkuDetailAsync(RequestScope scope, SportSkuDetailRequest request, RequestCallback<SportSkuDetailData> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getSportSkuDetail(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public SportSkuDetailData getSportSkuDetailSync(SportSkuDetailRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getSportSkuDetail(request));
    }

    @Override
    public long getDictMapAsync(RequestScope scope, DictMapRequest request, RequestCallback<List<DictMapData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getDictMap(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DictMapData> getDictMapSync(DictMapRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getDictMap(request));
    }

    @Override
    public long getSceneListAsync(RequestScope scope, SceneListRequest request, RequestCallback<List<SceneData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getSceneList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SceneData> getSceneListSync(SceneListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getSceneList(request));
    }

    @Override
    public long getWikiTypePageAsync(RequestScope scope, WikiTypePageRequest request, RequestCallback<BasePageData<WikiTypeData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getWikiTypePage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<WikiTypeData> getWikiTypePageSync(WikiTypePageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getWikiTypePage(request));
    }

    @Override
    public long getWikiPageAsync(RequestScope scope, WikiPageRequest request, RequestCallback<BasePageData<WikiData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getWikiPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<WikiData> getWikiPageSync(WikiPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getWikiPage(request));
    }

    @Override
    public long getWikiDetailAsync(RequestScope scope, WikiDetailRequest request, RequestCallback<WikiDetailData> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getWikiDetail(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public WikiDetailData getWikiDetailSync(WikiDetailRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getWikiDetail(request));
    }

    @Override
    public long uploadDeviceNameAsync(RequestScope scope, UploadDeviceNameRequest request, RequestCallback<Boolean> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.uploadDeviceName(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean uploadDeviceNameSync(UploadDeviceNameRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.uploadDeviceName(request));
    }

    @Override
    public long getDeviceFocusListAsync(RequestScope scope, DeviceFocusListRequest request, RequestCallback<List<DeviceFocusData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(operationService.getDeviceFocusList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DeviceFocusData> getDeviceFocusListSync(DeviceFocusListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(operationService.getDeviceFocusList(request));
    }
}
