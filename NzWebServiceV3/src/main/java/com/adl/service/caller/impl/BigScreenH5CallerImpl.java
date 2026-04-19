package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.BigScreenH5Caller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.http.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.http.request.TeachCourseButtonClickCountRequest;
import com.adl.service.http.request.TeachCourseDataDatesRequest;
import com.adl.service.http.request.TeachCourseRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassroomCompetitionRecordData;
import com.adl.service.data.SunshineRunningSportDetailData;
import com.adl.service.data.TeachCourseDetailData;
import com.adl.service.repository.BigScreenH5Repository;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * BigScreenH5Caller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class BigScreenH5CallerImpl implements BigScreenH5Caller {

    private final BigScreenH5Repository bigScreenH5Repository;

    public BigScreenH5CallerImpl() {
        this.bigScreenH5Repository = new BigScreenH5Repository();
    }

    @Override
    public long getTeachCourseDetailListAsync(RequestScope scope, TeachCourseRequest request, RequestCallback<List<TeachCourseDetailData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenH5Repository.getTeachCourseDetailList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<TeachCourseDetailData> getTeachCourseDetailListSync(TeachCourseRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenH5Repository.getTeachCourseDetailList(request));
    }

    @Override
    public long getTeachCourseDataDatesAsync(RequestScope scope, TeachCourseDataDatesRequest request, RequestCallback<List<String>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenH5Repository.getTeachCourseDataDates(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<String> getTeachCourseDataDatesSync(TeachCourseDataDatesRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenH5Repository.getTeachCourseDataDates(request));
    }

    @Override
    public long getTeachCourseTimesAsync(RequestScope scope, TeachCourseRequest request, RequestCallback<Integer> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenH5Repository.getTeachCourseTimes(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Integer getTeachCourseTimesSync(TeachCourseRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenH5Repository.getTeachCourseTimes(request));
    }

    @Override
    public long getTeachCourseButtonClickableAsync(RequestScope scope, TeachCourseButtonClickCountRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenH5Repository.getTeachCourseButtonClickable(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean getTeachCourseButtonClickableSync(TeachCourseButtonClickCountRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenH5Repository.getTeachCourseButtonClickable(request));
    }

    @Override
    public long getSunshineRunningSportDetailPageAsync(RequestScope scope, SunshineRunningSportDetailPageRequest request, RequestCallback<BasePageData<SunshineRunningSportDetailData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenH5Repository.getSunshineRunningSportDetailPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<SunshineRunningSportDetailData> getSunshineRunningSportDetailPageSync(SunshineRunningSportDetailPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenH5Repository.getSunshineRunningSportDetailPage(request));
    }

    @Override
    public long getSunshineRunningCompetitionRecordPageAsync(RequestScope scope, SunshineRunningCompetitionRecordPageRequest request, RequestCallback<BasePageData<ClassroomCompetitionRecordData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenH5Repository.getSunshineRunningCompetitionRecordPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<ClassroomCompetitionRecordData> getSunshineRunningCompetitionRecordPageSync(SunshineRunningCompetitionRecordPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenH5Repository.getSunshineRunningCompetitionRecordPage(request));
    }
}
