package com.adl.service.caller.impl;

import android.text.TextUtils;
import android.util.Pair;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.OrgTestCaller;
import com.adl.service.common.IDefine;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.PlanEntity;
import com.adl.service.db.entity.SportProjectEntity;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.log.NzLog;
import com.adl.service.persistence.BasePagePersistence;
import com.adl.service.persistence.BasePersistence;
import com.adl.service.persistence.OrgTestPersistenceHelper;
import com.adl.service.repository.OrgTestRepository;
import com.adl.service.utils.InnerUtil;
import com.adl.service.http.request.CheckStudentInPlanRequest;
import com.adl.service.http.request.PlanClassListRequest;
import com.adl.service.http.request.PlanInfoRequest;
import com.adl.service.http.request.PlanPageRequest;
import com.adl.service.http.request.PlanStudentPageRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.StandardConfigPageRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.callback.GetPageResult;
import com.adl.service.data.PlanClassData;
import com.adl.service.data.PlanData;
import com.adl.service.data.PlanInfoData;
import com.adl.service.data.PlanStudentData;
import com.adl.service.data.StandardConfigData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * OrgTestCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class OrgTestCallerImpl implements OrgTestCaller {

    private final OrgTestRepository orgTestRepository;

    public OrgTestCallerImpl() {
        this.orgTestRepository = new OrgTestRepository();
    }

    @Override
    public void reLoadAllOrgTestDataSync(boolean syncStandard) throws NzBaseException {
        long serverTime = InnerUtil.getServerTime();
        NzLog.d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));
        List<Integer> statusList = Arrays.asList(IDefine.SportPlanStatusNew, IDefine.SportPlanStatusDoing);

        ////////////// 1.同步全部运动计划 ////////////
        clearPlanDataSync();
        GetPageResult planSyncResult = new GetPageResult();
        for (Integer status : statusList) {
            PlanPageRequest planPageRequest = PlanPageRequest.builder()
                    .standardType(IDefine.standardTypeALL)
                    .status(String.valueOf(status))
                    .build();
            GetPageResult singleResult = getPlanPagesAllSync(planPageRequest);
            planSyncResult.setSuccessedPages(planSyncResult.getSuccessedPages() + singleResult.getSuccessedPages());
            planSyncResult.setFetchedCount(planSyncResult.getFetchedCount() + singleResult.getFetchedCount());
            planSyncResult.setPersistedCount(planSyncResult.getPersistedCount() + singleResult.getPersistedCount());
            planSyncResult.setTotalPages(planSyncResult.getTotalPages() + singleResult.getTotalPages());
        }
        planSyncResult.setSuccess(true);
        List<PlanEntity> planDataList = DaoManagerProxy.getInstance().getPlanDao().getAll();
        NzLog.d("reGetAllOrgTestDataSync merged plan count: " + planDataList.size() + ", request result: " + planSyncResult);

        ////////////// 2.同步运动计划下学生 ////////////
        clearPlanStudentDataSync();
        for (PlanEntity plan : planDataList) {
            if (TextUtils.isEmpty(plan.getEndTime()) || serverTime > Long.parseLong(plan.getEndTime())) {
                continue;
            }
            PlanStudentPageRequest request = PlanStudentPageRequest.builder(plan.getPlanId())
                    .build();
            getPlanStudentPagesAllSync(request);
        }

        ////////////// 3.同步运动标准 ////////////
        if (syncStandard) {
            clearStandardConfigDataSync();
            ArrayList<Pair<String, String>> standardList = new ArrayList<>();

            for (PlanEntity plan : planDataList) {
                if (plan.getSportProjects() == null) {
                    continue;
                }

                if (TextUtils.isEmpty(plan.getEndTime()) || serverTime > Long.parseLong(plan.getEndTime())) {
                    continue;
                }

                for (SportProjectEntity project : plan.getSportProjects()) {
                    Pair<String, String> newPair = Pair.create(plan.getStandardId(), project.getSportSkuId());
                    //  不包含添加
                    if (standardList.stream().noneMatch(standard -> standard.equals(newPair))) {
                        standardList.add(newPair);
                    }
                }
            }

            for (Pair<String, String> param : standardList) {
                StandardConfigPageRequest request = StandardConfigPageRequest.builder("")
                        .standardId(param.first)
                        .sportProjectCode(param.second)
                        .build();
                getStandardConfigPagesAllSync(request);
            }
        }
    }

    @Override
    public void clearStandardConfigDataSync() throws NzBaseException {
        RxCallbackScheduler.blockingGet(orgTestRepository.clearStandardConfigData());
    }

    @Override
    public long getStandardConfigPagesAllAsync(RequestScope scope, StandardConfigPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getStandardConfigPagesAll(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getStandardConfigPagesAllSync(StandardConfigPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(orgTestRepository.getStandardConfigPagesAll(request));
    }

    @Override
    public long getStandardConfigPageAsync(RequestScope scope, StandardConfigPageRequest request, RequestCallback<List<StandardConfigData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getStandardConfigPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<StandardConfigData> getStandardConfigPageSync(StandardConfigPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(orgTestRepository.getStandardConfigPage(request));
    }

    @Override
    public void clearPlanStudentDataSync() throws NzBaseException {
        RxCallbackScheduler.blockingGet(orgTestRepository.clearPlanStudentData());
    }

    @Override
    public long getPlanStudentPagesAllASync(RequestScope scope, PlanStudentPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getPlanStudentPagesAll(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getPlanStudentPagesAllSync(PlanStudentPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(orgTestRepository.getPlanStudentPagesAll(request));
    }

    @Override
    public long getPlanStudentPageAsync(RequestScope scope, PlanStudentPageRequest request, RequestCallback<BasePageData<PlanStudentData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getPlanStudentPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<PlanStudentData> getPlanStudentPageSync(PlanStudentPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(orgTestRepository.getPlanStudentPage(request));
    }

    @Override
    public void clearPlanDataSync() throws NzBaseException {
        RxCallbackScheduler.blockingGet(orgTestRepository.clearPlanData());
    }

    @Override
    public long getPlanPagesAllAsync(RequestScope scope, PlanPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getPlanPagesAll(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getPlanPagesAllSync(PlanPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(orgTestRepository.getPlanPagesAll(request));
    }

    @Override
    public long getPlanPageAsync(RequestScope scope, PlanPageRequest request, RequestCallback<BasePageData<PlanData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getPlanPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<PlanData> getPlanPageSync(PlanPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(orgTestRepository.getPlanPage(request));
    }

    @Override
    public long getPlanInfoAsync(RequestScope scope, PlanInfoRequest request, RequestCallback<PlanInfoData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getPlanInfo(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public PlanInfoData getPlanInfoSync(PlanInfoRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(orgTestRepository.getPlanInfo(request));
    }

    @Override
    public long getPlanClassListAsync(RequestScope scope, PlanClassListRequest request, RequestCallback<List<PlanClassData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.getPlanClassList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<PlanClassData> getPlanClassListSync(PlanClassListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(orgTestRepository.getPlanClassList(request));
    }

    @Override
    public long checkStudentInPlanAsync(RequestScope scope, CheckStudentInPlanRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(orgTestRepository.checkStudentInPlan(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean checkStudentInPlanSync(CheckStudentInPlanRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(orgTestRepository.checkStudentInPlan(request));
    }
}
