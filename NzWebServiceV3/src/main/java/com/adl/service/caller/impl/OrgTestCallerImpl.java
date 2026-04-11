package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.OrgTestCaller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.web.request.CheckStudentInPlanRequest;
import com.adl.service.web.request.PlanClassListRequest;
import com.adl.service.web.request.PlanListRequest;
import com.adl.service.web.request.PlanStudentPageRequest;
import com.adl.service.web.request.RecordDetailPageRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.StandardConfigPageRequest;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.PlanClassData;
import com.adl.service.web.response.PlanInfoData;
import com.adl.service.web.response.PlanData;
import com.adl.service.web.response.PlanStudentData;
import com.adl.service.web.response.RecordDetailData;
import com.adl.service.web.response.StandardConfigPageData;
import com.adl.service.web.service.OrgTestService;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * OrgTestCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class OrgTestCallerImpl implements OrgTestCaller {

    private final OrgTestService orgTestService;

    public OrgTestCallerImpl(OrgTestService orgTestService) {
        this.orgTestService = orgTestService;
    }

    @Override
    public long getStandardConfigPageAsync(RequestScope scope, StandardConfigPageRequest request, RequestCallback<List<StandardConfigPageData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(orgTestService.getStandardConfigPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<StandardConfigPageData> getStandardConfigPageSync(StandardConfigPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(orgTestService.getStandardConfigPage(request));
    }

    @Override
    public long getRecordDetailPageAsync(RequestScope scope, RecordDetailPageRequest request, RequestCallback<BasePageData<RecordDetailData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(orgTestService.getRecordDetailPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<RecordDetailData> getRecordDetailPageSync(RecordDetailPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(orgTestService.getRecordDetailPage(request));
    }

    @Override
    public long getPlanStudentPageAsync(RequestScope scope, PlanStudentPageRequest request, RequestCallback<BasePageData<PlanStudentData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(orgTestService.getPlanStudentPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<PlanStudentData> getPlanStudentPageSync(PlanStudentPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(orgTestService.getPlanStudentPage(request));
    }

    @Override
    public long getPlanPageAsync(RequestScope scope, PlanListRequest request, RequestCallback<BasePageData<PlanData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(orgTestService.getPlanPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<PlanData> getPlanPageSync(PlanListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(orgTestService.getPlanPage(request));
    }

    @Override
    public long getPlanInfoAsync(RequestScope scope, RequestCallback<PlanInfoData> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(orgTestService.getPlanInfo(), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public PlanInfoData getPlanInfoSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(orgTestService.getPlanInfo());
    }

    @Override
    public long getPlanClassListAsync(RequestScope scope, PlanClassListRequest request, RequestCallback<List<PlanClassData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(orgTestService.getPlanClassList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<PlanClassData> getPlanClassListSync(PlanClassListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(orgTestService.getPlanClassList(request));
    }

    @Override
    public long checkStudentInPlanAsync(RequestScope scope, CheckStudentInPlanRequest request, RequestCallback<Boolean> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(orgTestService.checkStudentInPlan(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean checkStudentInPlanSync(CheckStudentInPlanRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(orgTestService.checkStudentInPlan(request));
    }
}
