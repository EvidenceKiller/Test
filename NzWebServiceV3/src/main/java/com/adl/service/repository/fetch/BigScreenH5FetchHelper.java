package com.adl.service.repository.fetch;

import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassroomCompetitionRecordData;
import com.adl.service.data.SunshineRunningSportDetailData;
import com.adl.service.data.TeachCourseDetailData;
import com.adl.service.http.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.http.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.http.request.TeachCourseButtonClickCountRequest;
import com.adl.service.http.request.TeachCourseDataDatesRequest;
import com.adl.service.http.request.TeachCourseRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.BigScreenH5Service;

import java.util.List;

import io.reactivex.rxjava3.functions.Function;

public final class BigScreenH5FetchHelper {

    private final BigScreenH5Service bigScreenH5Service;

    public BigScreenH5FetchHelper() {
        this.bigScreenH5Service = RetrofitManager.getInstance().create(BigScreenH5Service.class);
    }

    public Function<TeachCourseRequest, List<TeachCourseDetailData>> createGetTeachCourseDetailListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenH5Service.getTeachCourseDetailList(request));
    }

    public Function<TeachCourseDataDatesRequest, List<String>> createGetTeachCourseDataDatesFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenH5Service.getTeachCourseDataDates(request));
    }

    public Function<TeachCourseRequest, Integer> createGetTeachCourseTimesFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenH5Service.getTeachCourseTimes(request));
    }

    public Function<TeachCourseButtonClickCountRequest, Boolean> createGetTeachCourseButtonClickableFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenH5Service.getTeachCourseButtonClickable(request));
    }

    public Function<SunshineRunningSportDetailPageRequest, BasePageData<SunshineRunningSportDetailData>> createGetSunshineRunningSportDetailPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenH5Service.getSunshineRunningSportDetailPage(request));
    }

    public Function<SunshineRunningCompetitionRecordPageRequest, BasePageData<ClassroomCompetitionRecordData>> createGetSunshineRunningCompetitionRecordPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenH5Service.getSunshineRunningCompetitionRecordPage(request));
    }
}
