package com.adl.service.repository;

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
import com.adl.service.repository.fetch.SportInfoFetchHelper;
import com.adl.service.repository.persist.SportInfoPersistHelper;
import com.adl.service.repository.prepare.SportInfoPrepareHelper;

import io.reactivex.rxjava3.core.Single;

public final class SportInfoRepository {
    private final SportInfoPrepareHelper prepareHelper = new SportInfoPrepareHelper();
    private final SportInfoFetchHelper fetchHelper = new SportInfoFetchHelper();
    private final SportInfoPersistHelper persistHelper = new SportInfoPersistHelper();

    public SportInfoRepository() {
    }

    public Single<Boolean> updateStudentSport(ReportStudentSportRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createUpdateStudentSportFunc());
    }

    public Single<Boolean> reportTeacherSport(ReportTeacherSportRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createReportTeacherSportFunc());
    }

    public Single<Boolean> reportStudentTrain(ReportStudentSportRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createReportStudentTrainFunc());
    }

    public Single<Boolean> reportStudentSport(ReportStudentSportRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createReportStudentSportFunc());
    }

    public Single<Boolean> reportStudentPlan(ReportStudentPlanRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createReportStudentPlanFunc());
    }

    public Single<Boolean> reportStudentMeet(ReportStudentMeetRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createReportStudentMeetFunc());
    }

    public Single<Boolean> reportStudentCompetition(ReportStudentCompetitionRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createReportStudentCompetitionFunc());
    }

    public Single<Boolean> reportStudentAllSport(ReportStudentSportRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createReportStudentAllSportFunc());
    }

    public Single<BasePageData<OrgRecordData>> getOrgRecordPage(OrgRecordPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetOrgRecordPageFunc());
    }

    public Single<BasePageData<AccountRecordData>> getAccountRecordPage(AccountRecordPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetAccountRecordPageFunc());
    }
}
