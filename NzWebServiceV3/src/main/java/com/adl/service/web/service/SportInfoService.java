package com.adl.service.web.service;

import com.adl.service.web.request.AccountRecordPageRequest;
import com.adl.service.web.request.OrgRecordPageRequest;
import com.adl.service.web.request.ReportNfSportRequest;
import com.adl.service.web.request.ReportStudentCompetitionRequest;
import com.adl.service.web.request.ReportStudentMeetRequest;
import com.adl.service.web.request.ReportStudentPlanRequest;
import com.adl.service.web.request.ReportStudentSportRequest;
import com.adl.service.web.request.ReportTeacherSportRequest;
import com.adl.service.web.response.AccountRecordData;
import com.adl.service.web.response.AccountRecordPageData;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.BaseResponse;
import com.adl.service.web.response.OrgRecordData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API（v3new 生成）
 */
public interface SportInfoService {
    /**
     * 通用修改学生运动成绩
     */
    @POST("tis/device/sport/result/updateStudentSport")
    Single<BaseResponse<Boolean>> updateStudentSport(@Body ReportStudentSportRequest request);

    /**
     * 上报教师普通运动成绩
     */
    @POST("tis/device/sport/result/reportTeacherSport")
    Single<BaseResponse<Boolean>> reportTeacherSport(@Body ReportTeacherSportRequest request);

    /**
     * 上报学生特训成绩
     */
    @POST("tis/device/sport/result/reportStudentTrain")
    Single<BaseResponse<Boolean>> reportStudentTrain(@Body ReportStudentSportRequest request);

    /**
     * 上报学生普通运动成绩
     */
    @POST("tis/device/sport/result/reportStudentSport")
    Single<BaseResponse<Boolean>> reportStudentSport(@Body ReportStudentSportRequest request);

    /**
     * 上报学生组织测试成绩
     */
    @POST("tis/device/sport/result/reportStudentPlan")
    Single<BaseResponse<Boolean>> reportStudentPlan(@Body ReportStudentPlanRequest request);

    /**
     * 上报学生运动会成绩
     */
    @POST("tis/device/sport/result/reportStudentMeet")
    Single<BaseResponse<Boolean>> reportStudentMeet(@Body ReportStudentMeetRequest request);

    /**
     * 上报学生竞技赛成绩
     */
    @POST("tis/device/sport/result/reportStudentCompetition")
    Single<BaseResponse<Boolean>> reportStudentCompetition(@Body ReportStudentCompetitionRequest request);

    /**
     * 通用上报学生运动成绩接口
     */
    @POST("tis/device/sport/result/reportStudentAllSport")
    Single<BaseResponse<Boolean>> reportStudentAllSport(@Body ReportStudentSportRequest request);

    /**
     * 上报市民通运动成绩
     */
    @POST("tis/device/sport/result/reportNfSport")
    Single<BaseResponse<Boolean>> reportNfSport(@Body ReportNfSportRequest request);

    /**
     * 获取机构/年级运动记录分页
     */
    @POST("tis/device/sport/result/getOrgRecordPage")
    Single<BaseResponse<BasePageData<OrgRecordData>>> getOrgRecordPage(@Body OrgRecordPageRequest request);

    /**
     * 获取班级/个人运动记录分页
     */
    @POST("tis/device/sport/result/getAccountRecordPage")
    Single<BaseResponse<BasePageData<AccountRecordData>>> getAccountRecordPage(@Body AccountRecordPageRequest request);
}
