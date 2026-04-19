package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
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

/**
 * SportInfoCaller。
 */
public interface SportInfoCaller {

    /**
     * 通用修改学生运动成绩
     * <p>异步调用。</p>
     */
    long updateStudentSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback);

    /**
     * 通用修改学生运动成绩
     * <p>同步调用。</p>
     */
    Boolean updateStudentSportSync(ReportStudentSportRequest request) throws NzBaseException;

    /**
     * 上报教师普通运动成绩
     * <p>异步调用。</p>
     */
    long reportTeacherSportAsync(RequestScope scope, ReportTeacherSportRequest request, RequestCallback<Boolean> callback);

    /**
     * 上报教师普通运动成绩
     * <p>同步调用。</p>
     */
    Boolean reportTeacherSportSync(ReportTeacherSportRequest request) throws NzBaseException;

    /**
     * 上报学生特训成绩
     * <p>异步调用。</p>
     */
    long reportStudentTrainAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback);

    /**
     * 上报学生特训成绩
     * <p>同步调用。</p>
     */
    Boolean reportStudentTrainSync(ReportStudentSportRequest request) throws NzBaseException;

    /**
     * 上报学生普通运动成绩
     * <p>异步调用。</p>
     */
    long reportStudentSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback);

    /**
     * 上报学生普通运动成绩
     * <p>同步调用。</p>
     */
    Boolean reportStudentSportSync(ReportStudentSportRequest request) throws NzBaseException;

    /**
     * 上报学生组织测试成绩
     * <p>异步调用。</p>
     */
    long reportStudentPlanAsync(RequestScope scope, ReportStudentPlanRequest request, RequestCallback<Boolean> callback);

    /**
     * 上报学生组织测试成绩
     * <p>同步调用。</p>
     */
    Boolean reportStudentPlanSync(ReportStudentPlanRequest request) throws NzBaseException;

    /**
     * 上报学生运动会成绩
     * <p>异步调用。</p>
     */
    long reportStudentMeetAsync(RequestScope scope, ReportStudentMeetRequest request, RequestCallback<Boolean> callback);

    /**
     * 上报学生运动会成绩
     * <p>同步调用。</p>
     */
    Boolean reportStudentMeetSync(ReportStudentMeetRequest request) throws NzBaseException;

    /**
     * 上报学生竞技赛成绩
     * <p>异步调用。</p>
     */
    long reportStudentCompetitionAsync(RequestScope scope, ReportStudentCompetitionRequest request, RequestCallback<Boolean> callback);

    /**
     * 上报学生竞技赛成绩
     * <p>同步调用。</p>
     */
    Boolean reportStudentCompetitionSync(ReportStudentCompetitionRequest request) throws NzBaseException;

    /**
     * 通用上报学生运动成绩接口
     * <p>异步调用。</p>
     */
    long reportStudentAllSportAsync(RequestScope scope, ReportStudentSportRequest request, RequestCallback<Boolean> callback);

    /**
     * 通用上报学生运动成绩接口
     * <p>同步调用。</p>
     */
    Boolean reportStudentAllSportSync(ReportStudentSportRequest request) throws NzBaseException;

    /**
     * 获取机构/年级运动记录分页
     * <p>异步调用。</p>
     */
    long getOrgRecordPageAsync(RequestScope scope, OrgRecordPageRequest request, RequestCallback<BasePageData<OrgRecordData>> callback);

    /**
     * 获取机构/年级运动记录分页
     * <p>同步调用。</p>
     */
    BasePageData<OrgRecordData> getOrgRecordPageSync(OrgRecordPageRequest request) throws NzBaseException;

    /**
     * 获取班级/个人运动记录分页
     * <p>异步调用。</p>
     */
    long getAccountRecordPageAsync(RequestScope scope, AccountRecordPageRequest request, RequestCallback<BasePageData<AccountRecordData>> callback);

    /**
     * 获取班级/个人运动记录分页
     * <p>同步调用。</p>
     */
    BasePageData<AccountRecordData> getAccountRecordPageSync(AccountRecordPageRequest request) throws NzBaseException;
}
