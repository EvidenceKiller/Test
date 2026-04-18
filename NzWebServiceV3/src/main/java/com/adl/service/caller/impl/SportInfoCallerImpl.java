package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.SportInfoCaller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.http.request.AccountRecordPageRequest;
import com.adl.service.http.request.OrgRecordPageRequest;
import com.adl.service.http.request.ReportStudentCompetitionRequest;
import com.adl.service.http.request.ReportStudentMeetRequest;
import com.adl.service.http.request.ReportStudentPlanRequest;
import com.adl.service.http.request.ReportStudentSportRequest;
import com.adl.service.http.request.ReportTeacherSportRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.data.AccountRecordData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.OrgRecordData;
import com.adl.service.web.SportInfoService;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * SportInfoCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class SportInfoCallerImpl implements SportInfoCaller {

    private final SportInfoService sportInfoService;

    public SportInfoCallerImpl(SportInfoService sportInfoService) {
        this.sportInfoService = sportInfoService;
    }

    @Override
    public long updateStudentSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.updateStudentSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean updateStudentSportSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.updateStudentSport(request));
    }

    @Override
    public long reportTeacherSportAsync(RequestScope scope, ReportTeacherSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.reportTeacherSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportTeacherSportSync(ReportTeacherSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.reportTeacherSport(request));
    }

    @Override
    public long reportStudentTrainAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.reportStudentTrain(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentTrainSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.reportStudentTrain(request));
    }

    @Override
    public long reportStudentSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.reportStudentSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentSportSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.reportStudentSport(request));
    }

    @Override
    public long reportStudentPlanAsync(RequestScope scope, ReportStudentPlanRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.reportStudentPlan(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentPlanSync(ReportStudentPlanRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.reportStudentPlan(request));
    }

    @Override
    public long reportStudentMeetAsync(RequestScope scope, ReportStudentMeetRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.reportStudentMeet(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentMeetSync(ReportStudentMeetRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.reportStudentMeet(request));
    }

    @Override
    public long reportStudentCompetitionAsync(RequestScope scope, ReportStudentCompetitionRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.reportStudentCompetition(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentCompetitionSync(ReportStudentCompetitionRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.reportStudentCompetition(request));
    }

    @Override
    public long reportStudentAllSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.reportStudentAllSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentAllSportSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.reportStudentAllSport(request));
    }

    @Override
    public long getOrgRecordPageAsync(RequestScope scope, OrgRecordPageRequest request, RequestCallback<BasePageData<OrgRecordData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.getOrgRecordPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<OrgRecordData> getOrgRecordPageSync(OrgRecordPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.getOrgRecordPage(request));
    }

    @Override
    public long getAccountRecordPageAsync(RequestScope scope, AccountRecordPageRequest request, RequestCallback<BasePageData<AccountRecordData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(sportInfoService.getAccountRecordPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<AccountRecordData> getAccountRecordPageSync(AccountRecordPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(sportInfoService.getAccountRecordPage(request));
    }
}
