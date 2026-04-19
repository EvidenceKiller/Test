package com.adl.service.repository;

import com.adl.service.callback.GetPageResult;
import com.adl.service.data.BasePageData;
import com.adl.service.data.CompetitionData;
import com.adl.service.data.CompetitionRankDetailData;
import com.adl.service.data.GroupData;
import com.adl.service.data.GroupTeamData;
import com.adl.service.data.GroupTeamDetailData;
import com.adl.service.data.ResData;
import com.adl.service.data.ResTypeData;
import com.adl.service.data.SportMeetData;
import com.adl.service.data.TrainPlanInfoData;
import com.adl.service.data.TrainPlanOneDayProjectData;
import com.adl.service.http.request.CompetitionPageRequest;
import com.adl.service.http.request.CompetitionRankDetailRequest;
import com.adl.service.http.request.DownloadGroupTeamDetailPageRequest;
import com.adl.service.http.request.DownloadGroupTeamPageRequest;
import com.adl.service.http.request.GetGroupRequest;
import com.adl.service.http.request.JoinCompetitionRankRequest;
import com.adl.service.http.request.ResInfoRequest;
import com.adl.service.http.request.ResPageRequest;
import com.adl.service.http.request.ResTypeListRequest;
import com.adl.service.http.request.SaveGroupRequest;
import com.adl.service.http.request.SportMeetPageRequest;
import com.adl.service.http.request.TrainPlanInfoPageRequest;
import com.adl.service.http.request.TrainPlanOneDayProjectListRequest;
import com.adl.service.repository.fetch.BusinessFetchHelper;
import com.adl.service.repository.persist.BusinessPersistHelper;
import com.adl.service.repository.prepare.BusinessPrepareHelper;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public final class BusinessRepository {
    private final BusinessPrepareHelper prepareHelper = new BusinessPrepareHelper();
    private final BusinessFetchHelper fetchHelper = new BusinessFetchHelper();
    private final BusinessPersistHelper persistHelper = new BusinessPersistHelper();

    public BusinessRepository() {
    }

    public Single<Boolean> saveGroup(SaveGroupRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createSaveGroupFunc());
    }

    public Single<GroupData> getGroup(GetGroupRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetGroupFunc());
    }

    public Single<List<TrainPlanOneDayProjectData>> getTrainPlanOneDayProjectList(TrainPlanOneDayProjectListRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetTrainPlanOneDayProjectListFunc());
    }

    public Single<BasePageData<TrainPlanInfoData>> getTrainPlanInfoPage(TrainPlanInfoPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetTrainPlanInfoPageFunc());
    }

    public Single<Boolean> joinCompetitionRank(JoinCompetitionRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createJoinCompetitionRankFunc());
    }

    public Single<CompetitionRankDetailData> getCompetitionRankDetail(CompetitionRankDetailRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetCompetitionRankDetailFunc());
    }

    public Single<GetPageResult> getCompetitionPagesAll(CompetitionPageRequest request) {
        return BaseRepositoryUtil.repositoryPagesAsSingle(request,
                prepareHelper.createPrepareCompetitionPageFunc(),
                fetchHelper.createGetCompetitionPagesAllFunc(request),
                persistHelper.createPersistCompetitionPageFunc());
    }

    public Single<BasePageData<CompetitionData>> getCompetitionPage(CompetitionPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                fetchHelper.createGetCompetitionPageFunc(),
                persistHelper.createPersistCompetitionPageFunc());
    }

    public Single<GetPageResult> getSportMeetPagesAll(SportMeetPageRequest request) {
        return BaseRepositoryUtil.repositoryPagesAsSingle(request,
                prepareHelper.createPrepareSportMeetPageFunc(),
                fetchHelper.createGetSportMeetPagesAllFunc(request),
                persistHelper.createPersistSportMeetPageFunc(request));
    }

    public Single<BasePageData<SportMeetData>> getSportMeetPage(SportMeetPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                fetchHelper.createGetSportMeetPageFunc(),
                persistHelper.createPersistSportMeetPageFunc(request));
    }

    public Single<List<GroupTeamData>> downloadGroupTeamPage(DownloadGroupTeamPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createDownloadGroupTeamPageFunc());
    }

    public Single<List<GroupTeamDetailData>> downloadGroupTeamDetailPage(DownloadGroupTeamDetailPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createDownloadGroupTeamDetailPageFunc());
    }

    public Single<List<ResTypeData>> getResTypeList(ResTypeListRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetResTypeListFunc());
    }

    public Single<BasePageData<ResData>> getResPage(ResPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetResPageFunc());
    }

    public Single<ResData> getResInfo(ResInfoRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetResInfoFunc());
    }
}
