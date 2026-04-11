package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.BigScreenH5Caller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.web.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.web.request.TeachCourseButtonClickCountRequest;
import com.adl.service.web.request.TeachCourseDataDatesRequest;
import com.adl.service.web.request.TeachCourseRequest;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.ClassroomCompetitionRecordData;
import com.adl.service.web.response.SunshineRunningSportDetailData;
import com.adl.service.web.response.TeachCourseDetailData;
import com.adl.service.web.service.BigScreenH5Service;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * BigScreenH5Caller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class BigScreenH5CallerImpl implements BigScreenH5Caller {

    private final BigScreenH5Service bigScreenH5Service;

    public BigScreenH5CallerImpl(BigScreenH5Service bigScreenH5Service) {
        this.bigScreenH5Service = bigScreenH5Service;
    }

    @Override
    public long getTeachCourseDetailListAsync(RequestScope scope, TeachCourseRequest request, RequestCallback<List<TeachCourseDetailData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenH5Service.getTeachCourseDetailList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<TeachCourseDetailData> getTeachCourseDetailListSync(TeachCourseRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(bigScreenH5Service.getTeachCourseDetailList(request));
    }

    @Override
    public long getTeachCourseDataDatesAsync(RequestScope scope, TeachCourseDataDatesRequest request, RequestCallback<List<String>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenH5Service.getTeachCourseDataDates(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<String> getTeachCourseDataDatesSync(TeachCourseDataDatesRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(bigScreenH5Service.getTeachCourseDataDates(request));
    }

    @Override
    public long getTeachCourseTimesAsync(RequestScope scope, TeachCourseRequest request, RequestCallback<Integer> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenH5Service.getTeachCourseTimes(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Integer getTeachCourseTimesSync(TeachCourseRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(bigScreenH5Service.getTeachCourseTimes(request));
    }

    @Override
    public long getTeachCourseButtonClickCountAsync(RequestScope scope, TeachCourseButtonClickCountRequest request, RequestCallback<Boolean> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenH5Service.getTeachCourseButtonClickCount(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean getTeachCourseButtonClickCountSync(TeachCourseButtonClickCountRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(bigScreenH5Service.getTeachCourseButtonClickCount(request));
    }

    @Override
    public long getSunshineRunningSportDetailPageAsync(RequestScope scope, SunshineRunningSportDetailPageRequest request, RequestCallback<BasePageData<SunshineRunningSportDetailData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenH5Service.getSunshineRunningSportDetailPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<SunshineRunningSportDetailData> getSunshineRunningSportDetailPageSync(SunshineRunningSportDetailPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(bigScreenH5Service.getSunshineRunningSportDetailPage(request));
    }

    @Override
    public long getSunshineRunningCompetitionRecordPageAsync(RequestScope scope, SunshineRunningCompetitionRecordPageRequest request, RequestCallback<BasePageData<ClassroomCompetitionRecordData>> callback) {
        CallerScopeUtils.requireScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenH5Service.getSunshineRunningCompetitionRecordPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<ClassroomCompetitionRecordData> getSunshineRunningCompetitionRecordPageSync(SunshineRunningCompetitionRecordPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetData(bigScreenH5Service.getSunshineRunningCompetitionRecordPage(request));
    }
}
