package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.SportInfoCaller;
import com.adl.service.data.AccountRecordData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.OrgRecordData;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.AccountRecordPageRequest;
import com.adl.service.http.request.OrgRecordPageRequest;
import com.adl.service.http.request.ReportStudentCompetitionRequest;
import com.adl.service.http.request.ReportStudentMeetRequest;
import com.adl.service.http.request.ReportStudentPlanRequest;
import com.adl.service.http.request.ReportStudentSportRequest;
import com.adl.service.http.request.ReportTeacherSportRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.repository.SportInfoRepository;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * SportInfoCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class SportInfoCallerImpl implements SportInfoCaller {

    private final SportInfoRepository sportInfoRepository;

    public SportInfoCallerImpl() {
        this.sportInfoRepository = new SportInfoRepository();
    }

    @Override
    public long updateStudentSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.updateStudentSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean updateStudentSportSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.updateStudentSport(request));
    }

    @Override
    public long reportTeacherSportAsync(RequestScope scope, ReportTeacherSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.reportTeacherSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportTeacherSportSync(ReportTeacherSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.reportTeacherSport(request));
    }

    @Override
    public long reportStudentTrainAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.reportStudentTrain(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentTrainSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.reportStudentTrain(request));
    }

    @Override
    public long reportStudentSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.reportStudentSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentSportSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.reportStudentSport(request));
    }

    @Override
    public long reportStudentPlanAsync(RequestScope scope, ReportStudentPlanRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.reportStudentPlan(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentPlanSync(ReportStudentPlanRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.reportStudentPlan(request));
    }

    @Override
    public long reportStudentMeetAsync(RequestScope scope, ReportStudentMeetRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.reportStudentMeet(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentMeetSync(ReportStudentMeetRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.reportStudentMeet(request));
    }

    @Override
    public long reportStudentCompetitionAsync(RequestScope scope, ReportStudentCompetitionRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.reportStudentCompetition(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentCompetitionSync(ReportStudentCompetitionRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.reportStudentCompetition(request));
    }

    @Override
    public long reportStudentAllSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.reportStudentAllSport(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean reportStudentAllSportSync(ReportStudentSportRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.reportStudentAllSport(request));
    }

    @Override
    public long getOrgRecordPageAsync(RequestScope scope, OrgRecordPageRequest request, RequestCallback<BasePageData<OrgRecordData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.getOrgRecordPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<OrgRecordData> getOrgRecordPageSync(OrgRecordPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.getOrgRecordPage(request));
    }

    @Override
    public long getAccountRecordPageAsync(RequestScope scope, AccountRecordPageRequest request, RequestCallback<BasePageData<AccountRecordData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(sportInfoRepository.getAccountRecordPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<AccountRecordData> getAccountRecordPageSync(AccountRecordPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(sportInfoRepository.getAccountRecordPage(request));
    }
}
