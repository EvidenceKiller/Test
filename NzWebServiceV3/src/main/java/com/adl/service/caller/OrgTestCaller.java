package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
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

import java.util.List;

/**
 * OrgTestCaller。
 */
public interface OrgTestCaller {

    /**
     * 获取标准列表
     * <p>异步调用。</p>
     */
    long getStandardConfigPageAsync(RequestScope scope, StandardConfigPageRequest request, RequestCallback<List<StandardConfigPageData>> callback);

    /**
     * 获取标准列表
     * <p>同步调用。</p>
     */
    List<StandardConfigPageData> getStandardConfigPageSync(StandardConfigPageRequest request) throws NzBaseException;

    /**
     * 获取组织测试记录详情
     * <p>异步调用。</p>
     */
    long getRecordDetailPageAsync(RequestScope scope, RecordDetailPageRequest request, RequestCallback<BasePageData<RecordDetailData>> callback);

    /**
     * 获取组织测试记录详情
     * <p>同步调用。</p>
     */
    BasePageData<RecordDetailData> getRecordDetailPageSync(RecordDetailPageRequest request) throws NzBaseException;

    /**
     * 获取计划下学生
     * <p>异步调用。</p>
     */
    long getPlanStudentPageAsync(RequestScope scope, PlanStudentPageRequest request, RequestCallback<BasePageData<PlanStudentData>> callback);

    /**
     * 获取计划下学生
     * <p>同步调用。</p>
     */
    BasePageData<PlanStudentData> getPlanStudentPageSync(PlanStudentPageRequest request) throws NzBaseException;

    /**
     * 获取计划列表
     * <p>异步调用。</p>
     */
    long getPlanPageAsync(RequestScope scope, PlanListRequest request, RequestCallback<BasePageData<PlanData>> callback);

    /**
     * 获取计划列表
     * <p>同步调用。</p>
     */
    BasePageData<PlanData> getPlanPageSync(PlanListRequest request) throws NzBaseException;

    /**
     * 获取组织测试计划详情
     * <p>异步调用。</p>
     */
    long getPlanInfoAsync(RequestScope scope, RequestCallback<PlanInfoData> callback);

    /**
     * 获取组织测试计划详情
     * <p>同步调用。</p>
     */
    PlanInfoData getPlanInfoSync() throws NzBaseException;

    /**
     * 获取计划班级列表
     * <p>异步调用。</p>
     */
    long getPlanClassListAsync(RequestScope scope, PlanClassListRequest request, RequestCallback<List<PlanClassData>> callback);

    /**
     * 获取计划班级列表
     * <p>同步调用。</p>
     */
    List<PlanClassData> getPlanClassListSync(PlanClassListRequest request) throws NzBaseException;

    /**
     * 查询学生是否在计划下
     * <p>异步调用。</p>
     */
    long checkStudentInPlanAsync(RequestScope scope, CheckStudentInPlanRequest request, RequestCallback<Boolean> callback);

    /**
     * 查询学生是否在计划下
     * <p>同步调用。</p>
     */
    Boolean checkStudentInPlanSync(CheckStudentInPlanRequest request) throws NzBaseException;

}
