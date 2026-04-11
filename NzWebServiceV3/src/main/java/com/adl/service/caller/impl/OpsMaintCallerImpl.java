package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.OpsMaintCaller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.web.request.DeviceQrcodeRequest;
import com.adl.service.web.request.LogAddBatchRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.SoftwareReportRequest;
import com.adl.service.web.request.UpgradePlanByDeviceWithOutLoginRequest;
import com.adl.service.web.request.UploadDeviceInfoRequest;
import com.adl.service.web.request.UploadUpgradeLogRequest;
import com.adl.service.web.response.UpgradePlanByDeviceData;
import com.adl.service.web.service.OpsMaintService;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * OpsMaintCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class OpsMaintCallerImpl implements OpsMaintCaller {

    private final OpsMaintService opsMaintService;

    public OpsMaintCallerImpl(OpsMaintService opsMaintService) {
        this.opsMaintService = opsMaintService;
    }

    @Override
    public long uploadDeviceInfoAsync(RequestScope scope, UploadDeviceInfoRequest request, RequestCallback<Boolean> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(opsMaintService.uploadDeviceInfo(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean uploadDeviceInfoSync(UploadDeviceInfoRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(opsMaintService.uploadDeviceInfo(request));
    }

    @Override
    public long uploadUpgradeLogAsync(RequestScope scope, UploadUpgradeLogRequest request, RequestCallback<Object> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(opsMaintService.uploadUpgradeLog(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Object uploadUpgradeLogSync(UploadUpgradeLogRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(opsMaintService.uploadUpgradeLog(request));
    }

    @Override
    public long getUpgradePlanByDeviceAsync(RequestScope scope, RequestCallback<UpgradePlanByDeviceData> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(opsMaintService.getUpgradePlanByDevice(), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public UpgradePlanByDeviceData getUpgradePlanByDeviceSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(opsMaintService.getUpgradePlanByDevice());
    }

    @Override
    public long getUpgradePlanByDeviceWithOutLoginAsync(RequestScope scope, UpgradePlanByDeviceWithOutLoginRequest request, RequestCallback<String> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(opsMaintService.getUpgradePlanByDeviceWithOutLogin(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public String getUpgradePlanByDeviceWithOutLoginSync(UpgradePlanByDeviceWithOutLoginRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(opsMaintService.getUpgradePlanByDeviceWithOutLogin(request));
    }

    @Override
    public long softwareReportAsync(RequestScope scope, SoftwareReportRequest request, RequestCallback<Boolean> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(opsMaintService.softwareReport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean softwareReportSync(SoftwareReportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(opsMaintService.softwareReport(request));
    }

    @Override
    public long getDeviceQrcodeAsync(RequestScope scope, DeviceQrcodeRequest request, RequestCallback<String> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(opsMaintService.getDeviceQrcode(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public String getDeviceQrcodeSync(DeviceQrcodeRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(opsMaintService.getDeviceQrcode(request));
    }

    @Override
    public long logAddBatchAsync(RequestScope scope, LogAddBatchRequest request, RequestCallback<Object> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(opsMaintService.logAddBatch(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Object logAddBatchSync(LogAddBatchRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(opsMaintService.logAddBatch(request));
    }
}
