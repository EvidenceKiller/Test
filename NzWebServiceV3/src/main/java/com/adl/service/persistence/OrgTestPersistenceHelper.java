package com.adl.service.persistence;

import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.PlanEntity;
import com.adl.service.db.entity.PlanStudentEntity;
import com.adl.service.db.entity.StandardConfigEntity;
import com.adl.service.http.request.PlanPageRequest;
import com.adl.service.http.request.PlanStudentPageRequest;
import com.adl.service.http.request.StandardConfigPageRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.PlanData;
import com.adl.service.data.PlanStudentData;
import com.adl.service.data.StandardConfigData;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public final class OrgTestPersistenceHelper {

    private OrgTestPersistenceHelper() {
    }

    public static Function<Void, Completable> createStandardConfigPagePreparer() {
        return (v) -> {
            DaoManagerProxy.getInstance().getStandardConfigDao().clearAll();
            return Completable.complete();
        };
    }

    /**
     * 为 Plan 分页请求构建通用的 PageRequester
     */
    public static Function<Long, Single<BaseResponse<List<StandardConfigData>>>> createStandardConfigPageRequester(
            StandardConfigPageRequest request, Function<StandardConfigPageRequest, Single<BaseResponse<List<StandardConfigData>>>> function) {
        return (page) -> {
            StandardConfigPageRequest pageReq = StandardConfigPageRequest.builder(request.getStandardType())
                    .current(page)
                    .pageSize(request.getPageSize())
                    .standardId(request.getStandardId())
                    .sportProjectCode(request.getSportProjectCode())
                    .build();
            return function.apply(pageReq);
        };
    }

    /**
     * 为 Plan 数据构建通用的 Persister
     */
    public static Function<List<StandardConfigData>, List<StandardConfigData>> createStandardConfigPagePersister() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            List<StandardConfigEntity> entityList = dataList.stream()
                    .map(data -> StandardConfigEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getStandardConfigDao().insertAll(entityList);
            return dataList;
        };
    }

    public static Function<Void, Completable> createPlanStudentPagePreparer(String planId) {
        return (v) -> {
            DaoManagerProxy.getInstance().getPlanStudentDao().clearByPlanId(planId);
            return Completable.complete();
        };
    }


    /**
     * 为 Plan 分页请求构建通用的 PageRequester
     */
    public static Function<Long, Single<BaseResponse<BasePageData<PlanStudentData>>>> createPlanStudentPageRequester(
            PlanStudentPageRequest request, Function<PlanStudentPageRequest, Single<BaseResponse<BasePageData<PlanStudentData>>>> function) {
        return (page) -> {
            PlanStudentPageRequest pageReq = PlanStudentPageRequest.builder(request.getPlanId())
                    .current(page)
                    .pageSize(request.getPageSize())
                    .orgId(request.getOrgId())
                    .classId(request.getClassId())
                    .keyword(request.getKeyword())
                    .build();
            return function.apply(pageReq);
        };
    }

    /**
     * 为 Plan 数据构建通用的 Persister
     */
    public static Function<List<PlanStudentData>, List<PlanStudentData>> createPlanStudentPagePersister(String planId) {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            List<PlanStudentEntity> entityList = dataList.stream()
                    .map(data -> PlanStudentEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            entityList.forEach(plan -> plan.setPlanId(planId));
            DaoManagerProxy.getInstance().getPlanStudentDao().insertAll(entityList);
            return dataList;
        };
    }

    public static Function<Void, Completable> createPlanPagePagePreparer() {
        return (v) -> {
            DaoManagerProxy.getInstance().getPlanDao().clearAll();
            return Completable.complete();
        };
    }

    /**
     * 为 Plan 分页请求构建通用的 PageRequester
     */
    public static Function<Long, Single<BaseResponse<BasePageData<PlanData>>>> createPlanPageRequester(
            PlanPageRequest request, Function<PlanPageRequest, Single<BaseResponse<BasePageData<PlanData>>>> function) {
        return (page) -> {
            PlanPageRequest pageReq = PlanPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .orgId(request.getOrgId())
                    .standardType(request.getStandardType())
                    .acayearCode(request.getAcayearCode())
                    .status(request.getStatus())
                    .showStatistic(request.getShowStatistic())
                    .planId(request.getPlanId())
                    .build();
            return function.apply(pageReq);
        };
    }

    /**
     * 为 Plan 数据构建通用的 Persister
     */
    public static Function<List<PlanData>, List<PlanData>> createPlanPagePersister() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            // 将 PlanData 转换为 PlanEntity
            List<PlanEntity> entityList = dataList.stream()
                    .map(data -> PlanEntity.convertToEntity(data))
                    .collect(Collectors.toList());

            DaoManagerProxy.getInstance().getPlanDao().insertPlanList(entityList);
            return dataList;
        };
    }
}
