package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.CheckStudentInPlanRequest;
import com.adl.service.http.request.PlanClassListRequest;
import com.adl.service.http.request.PlanInfoRequest;
import com.adl.service.http.request.PlanPageRequest;
import com.adl.service.http.request.PlanStudentPageRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.StandardConfigPageRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.PlanClassData;
import com.adl.service.data.PlanInfoData;
import com.adl.service.data.PlanData;
import com.adl.service.data.PlanStudentData;
import com.adl.service.callback.GetPageResult;
import com.adl.service.data.StandardConfigData;

import java.util.List;

/**
 * OrgTestCaller。
 */
public interface OrgTestCaller {

    void reLoadAllOrgTestDataSync(boolean syncStandard) throws NzBaseException;

    void clearStandardConfigDataSync() throws NzBaseException;

    long getStandardConfigPagesAllAsync(RequestScope scope, StandardConfigPageRequest request, RequestCallback<GetPageResult> callback);

    GetPageResult getStandardConfigPagesAllSync(StandardConfigPageRequest request) throws NzBaseException;

    /**
     * 获取标准列表
     * <p>异步调用。</p>
     */
    long getStandardConfigPageAsync(RequestScope scope, StandardConfigPageRequest request, RequestCallback<List<StandardConfigData>> callback);

    /**
     * 获取标准列表
     * <p>同步调用。</p>
     */
    List<StandardConfigData> getStandardConfigPageSync(StandardConfigPageRequest request) throws NzBaseException;

    void clearPlanStudentDataSync() throws NzBaseException;

    long getPlanStudentPagesAllASync(RequestScope scope, PlanStudentPageRequest request, RequestCallback<GetPageResult> callback);

    GetPageResult getPlanStudentPagesAllSync(PlanStudentPageRequest request) throws NzBaseException;

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

    void clearPlanDataSync() throws NzBaseException;

    /**
     * 获取所有的分页计划列表
     * <p>异步调用。</p>
     *
     * @param scope
     * @param request
     * @param callback
     * @return
     * @throws NzBaseException
     */
    long getPlanPagesAllAsync(RequestScope scope, PlanPageRequest request, RequestCallback<GetPageResult> callback);

    /**
     * 获取所有的分页计划列表
     * <p>同步调用。</p>
     *
     * @param request
     * @return
     * @throws NzBaseException
     */
    GetPageResult getPlanPagesAllSync(PlanPageRequest request) throws NzBaseException;

    /**
     * 获取计划列表
     * <p>异步调用。</p>
     */
    long getPlanPageAsync(RequestScope scope, PlanPageRequest request, RequestCallback<BasePageData<PlanData>> callback);

    /**
     * 获取计划列表
     * <p>同步调用。</p>
     */
    BasePageData<PlanData> getPlanPageSync(PlanPageRequest request) throws NzBaseException;

    /**
     * 获取组织测试计划详情
     * <p>异步调用。</p>
     */
    long getPlanInfoAsync(RequestScope scope, PlanInfoRequest request, RequestCallback<PlanInfoData> callback);

    /**
     * 获取组织测试计划详情
     * <p>同步调用。</p>
     */
    PlanInfoData getPlanInfoSync(PlanInfoRequest request) throws NzBaseException;

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
