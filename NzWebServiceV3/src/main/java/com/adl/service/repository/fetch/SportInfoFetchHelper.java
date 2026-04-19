package com.adl.service.repository.fetch;

import com.adl.service.data.AccountRecordData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.OrgRecordData;
import com.adl.service.http.request.AccountRecordPageRequest;
import com.adl.service.http.request.OrgRecordPageRequest;
import com.adl.service.http.request.ReportStudentCompetitionRequest;
import com.adl.service.http.request.ReportStudentMeetRequest;
import com.adl.service.http.request.ReportStudentPlanRequest;
import com.adl.service.http.request.ReportStudentSportRequest;
import com.adl.service.http.request.ReportTeacherSportRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.SportInfoService;

import io.reactivex.rxjava3.functions.Function;

public final class SportInfoFetchHelper {

    private final SportInfoService sportInfoService;

    public SportInfoFetchHelper() {
        this.sportInfoService = RetrofitManager.getInstance().create(SportInfoService.class);
    }

    public Function<ReportStudentSportRequest, Boolean> createUpdateStudentSportFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.updateStudentSport(request));
    }

    public Function<ReportTeacherSportRequest, Boolean> createReportTeacherSportFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.reportTeacherSport(request));
    }

    public Function<ReportStudentSportRequest, Boolean> createReportStudentTrainFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.reportStudentTrain(request));
    }

    public Function<ReportStudentSportRequest, Boolean> createReportStudentSportFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.reportStudentSport(request));
    }

    public Function<ReportStudentPlanRequest, Boolean> createReportStudentPlanFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.reportStudentPlan(request));
    }

    public Function<ReportStudentMeetRequest, Boolean> createReportStudentMeetFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.reportStudentMeet(request));
    }

    public Function<ReportStudentCompetitionRequest, Boolean> createReportStudentCompetitionFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.reportStudentCompetition(request));
    }

    public Function<ReportStudentSportRequest, Boolean> createReportStudentAllSportFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.reportStudentAllSport(request));
    }

    public Function<OrgRecordPageRequest, BasePageData<OrgRecordData>> createGetOrgRecordPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.getOrgRecordPage(request));
    }

    public Function<AccountRecordPageRequest, BasePageData<AccountRecordData>> createGetAccountRecordPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(sportInfoService.getAccountRecordPage(request));
    }
}
