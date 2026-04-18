package com.adl.service.web;

import com.adl.service.http.request.CheckStudentInPlanRequest;
import com.adl.service.http.request.PlanClassListRequest;
import com.adl.service.http.request.PlanInfoRequest;
import com.adl.service.http.request.PlanPageRequest;
import com.adl.service.http.request.PlanStudentPageRequest;
import com.adl.service.http.request.StandardConfigPageRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.PlanClassData;
import com.adl.service.data.PlanInfoData;
import com.adl.service.data.PlanData;
import com.adl.service.data.PlanStudentData;
import com.adl.service.data.StandardConfigData;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API（v3new 生成）
 */
public interface OrgTestService {
    /**
     * 获取标准列表
     */
    @POST("tis/device/pte/organizeTest/getStandardConfig")
    BaseResponse<List<StandardConfigData>> getStandardConfigPage(@Body StandardConfigPageRequest request);

    /**
     * 获取计划下学生
     */
    @POST("tis/device/pte/organizeTest/getPlanStudents")
    BaseResponse<BasePageData<PlanStudentData>> getPlanStudentPage(@Body PlanStudentPageRequest request);

    /**
     * 获取计划列表
     */
    @POST("tis/device/pte/organizeTest/getPlanList")
    BaseResponse<BasePageData<PlanData>> getPlanPage(@Body PlanPageRequest request);

    /**
     * 获取组织测试计划详情
     */
    @POST("tis/device/pte/organizeTest/getPlanInfo")
    BaseResponse<PlanInfoData> getPlanInfo(@Body PlanInfoRequest request);

    /**
     * 获取计划班级列表
     */
    @POST("tis/device/pte/organizeTest/getPlanClassList")
    BaseResponse<List<PlanClassData>> getPlanClassList(@Body PlanClassListRequest request);

    /**
     * 查询学生是否在计划下
     */
    @POST("tis/device/pte/organizeTest/checkStudent")
    BaseResponse<Boolean> checkStudentInPlan(@Body CheckStudentInPlanRequest request);
}
