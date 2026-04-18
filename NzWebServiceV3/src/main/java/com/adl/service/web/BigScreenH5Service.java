package com.adl.service.web;

import com.adl.service.http.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.http.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.http.request.TeachCourseButtonClickCountRequest;
import com.adl.service.http.request.TeachCourseDataDatesRequest;
import com.adl.service.http.request.TeachCourseRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.ClassroomCompetitionRecordData;
import com.adl.service.data.SunshineRunningSportDetailData;
import com.adl.service.data.TeachCourseDetailData;

import java.util.List;

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
    BaseResponse<List<TeachCourseDetailData>> getTeachCourseDetailList(@Body TeachCourseRequest request);

    /**
     * 有数据的日期列表
     */
    @POST("tis/device/h5/org/teach/course/dataDates")
    BaseResponse<List<String>> getTeachCourseDataDates(@Body TeachCourseDataDatesRequest request);

    /**
     * 最大次数
     */
    @POST("tis/device/h5/org/teach/course/courseTimes")
    BaseResponse<Integer> getTeachCourseTimes(@Body TeachCourseRequest request);

    /**
     * 教学按钮点击计数
     */
    @POST("tis/device/h5/org/teach/course/click/teachButton")
    BaseResponse<Boolean> getTeachCourseButtonClickable(@Body TeachCourseButtonClickCountRequest request);

    /**
     * 历史记录
     */
    @POST("tis/device/h5/org/sunshineRunning/sportDetailList")
    BaseResponse<BasePageData<SunshineRunningSportDetailData>> getSunshineRunningSportDetailPage(@Body SunshineRunningSportDetailPageRequest request);

    /**
     * 历史记录
     */
    @POST("tis/device/h5/org/sunshineRunning/getClassroomCompetitionRecordList")
    BaseResponse<BasePageData<ClassroomCompetitionRecordData>> getSunshineRunningCompetitionRecordPage(@Body SunshineRunningCompetitionRecordPageRequest request);
}
