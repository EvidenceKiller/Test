package com.adl.service.web.service;

import com.adl.service.web.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.web.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.web.request.TeachCourseButtonClickCountRequest;
import com.adl.service.web.request.TeachCourseDataDatesRequest;
import com.adl.service.web.request.TeachCourseRequest;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.BaseResponse;
import com.adl.service.web.response.ClassroomCompetitionRecordData;
import com.adl.service.web.response.SunshineRunningSportDetailData;
import com.adl.service.web.response.TeachCourseDetailData;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API（v3new 生成）
 */
public interface BigScreenH5Service {
    /**
     * 分组教学列表
     */
    @POST("tis/device/h5/org/teach/course/detail/list")
    Single<BaseResponse<List<TeachCourseDetailData>>> getTeachCourseDetailList(@Body TeachCourseRequest request);

    /**
     * 有数据的日期列表
     */
    @POST("tis/device/h5/org/teach/course/dataDates")
    Single<BaseResponse<List<String>>> getTeachCourseDataDates(@Body TeachCourseDataDatesRequest request);

    /**
     * 最大次数
     */
    @POST("tis/device/h5/org/teach/course/courseTimes")
    Single<BaseResponse<Integer>> getTeachCourseTimes(@Body TeachCourseRequest request);

    /**
     * 教学按钮点击计数
     */
    @POST("tis/device/h5/org/teach/course/click/teachButton")
    Single<BaseResponse<Boolean>> getTeachCourseButtonClickCount(@Body TeachCourseButtonClickCountRequest request);

    /**
     * 历史记录
     */
    @POST("tis/device/h5/org/sunshineRunning/sportDetailList")
    Single<BaseResponse<BasePageData<SunshineRunningSportDetailData>>> getSunshineRunningSportDetailPage(@Body SunshineRunningSportDetailPageRequest request);

    /**
     * 历史记录
     */
    @POST("tis/device/h5/org/sunshineRunning/getClassroomCompetitionRecordList")
    Single<BaseResponse<BasePageData<ClassroomCompetitionRecordData>>> getSunshineRunningCompetitionRecordPage(@Body SunshineRunningCompetitionRecordPageRequest request);
}
