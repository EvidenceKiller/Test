package com.adl.service.repository.fetch;

import com.adl.service.data.BasePageData;
import com.adl.service.data.PlanClassData;
import com.adl.service.data.PlanInfoData;
import com.adl.service.data.PlanData;
import com.adl.service.data.PlanStudentData;
import com.adl.service.data.StandardConfigData;
import com.adl.service.http.request.CheckStudentInPlanRequest;
import com.adl.service.http.request.PlanClassListRequest;
import com.adl.service.http.request.PlanInfoRequest;
import com.adl.service.http.request.PlanPageRequest;
import com.adl.service.http.request.PlanStudentPageRequest;
import com.adl.service.http.request.StandardConfigPageRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.OrgTestService;

import java.util.List;

import io.reactivex.rxjava3.functions.Function;

public final class OrgTestFetchHelper {

    private final OrgTestService orgTestService;

    public OrgTestFetchHelper() {
        this.orgTestService = RetrofitManager.getInstance().create(OrgTestService.class);
    }

    public Function<Long, List<StandardConfigData>> createGetStandardConfigPagesAllFunc(StandardConfigPageRequest request) {
        return (page) -> {
            StandardConfigPageRequest pageReq = StandardConfigPageRequest.builder(request.getStandardType())
                    .current(page)
                    .pageSize(request.getPageSize())
                    .standardId(request.getStandardId())
                    .sportProjectCode(request.getSportProjectCode())
                    .build();
            return BaseFetchUtil.scheduleResponse(orgTestService.getStandardConfigPage(pageReq));
        };
    }

    public Function<StandardConfigPageRequest, List<StandardConfigData>> createGetStandardConfigPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(orgTestService.getStandardConfigPage(request));
    }

    public Function<Long, BasePageData<PlanStudentData>> createGetPlanStudentPagesAllFunc(PlanStudentPageRequest request) {
        return (page) -> {
            PlanStudentPageRequest pageReq = PlanStudentPageRequest.builder(request.getPlanId())
                    .current(page)
                    .pageSize(request.getPageSize())
                    .orgId(request.getOrgId())
                    .classId(request.getClassId())
                    .keyword(request.getKeyword())
                    .build();
            return BaseFetchUtil.scheduleResponse(orgTestService.getPlanStudentPage(pageReq));
        };
    }

    public Function<PlanStudentPageRequest, BasePageData<PlanStudentData>> createGetPlanStudentPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(orgTestService.getPlanStudentPage(request));
    }

    public Function<Long, BasePageData<PlanData>> createGetPlanPagesAllFunc(PlanPageRequest request) {
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
            return BaseFetchUtil.scheduleResponse(orgTestService.getPlanPage(pageReq));
        };
    }

    public Function<PlanPageRequest, BasePageData<PlanData>> createGetPlanPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(orgTestService.getPlanPage(request));
    }

    public Function<PlanInfoRequest, PlanInfoData> createGetPlanInfoFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(orgTestService.getPlanInfo(request));
    }

    public Function<PlanClassListRequest, List<PlanClassData>> createGetPlanClassListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(orgTestService.getPlanClassList(request));
    }

    public Function<CheckStudentInPlanRequest, Boolean> createCheckStudentInPlanFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(orgTestService.checkStudentInPlan(request));
    }
}
