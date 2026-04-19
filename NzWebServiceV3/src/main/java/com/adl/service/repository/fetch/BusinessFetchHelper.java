package com.adl.service.repository.fetch;

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
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.BusinessService;

import java.util.List;

import io.reactivex.rxjava3.functions.Function;

public final class BusinessFetchHelper {

    private final BusinessService businessService;

    public BusinessFetchHelper() {
        this.businessService = RetrofitManager.getInstance().create(BusinessService.class);
    }

    public Function<SaveGroupRequest, Boolean> createSaveGroupFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.saveGroup(request));
    }

    public Function<GetGroupRequest, GroupData> createGetGroupFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getGroup(request));
    }

    public Function<TrainPlanOneDayProjectListRequest, List<TrainPlanOneDayProjectData>> createGetTrainPlanOneDayProjectListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getTrainPlanOneDayProjectList(request));
    }

    public Function<TrainPlanInfoPageRequest, BasePageData<TrainPlanInfoData>> createGetTrainPlanInfoPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getTrainPlanInfoPage(request));
    }

    public Function<JoinCompetitionRankRequest, Boolean> createJoinCompetitionRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.joinCompetitionRank(request));
    }

    public Function<CompetitionRankDetailRequest, CompetitionRankDetailData> createGetCompetitionRankDetailFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getCompetitionRankDetail(request));
    }

    public Function<Long, BasePageData<CompetitionData>> createGetCompetitionPagesAllFunc(CompetitionPageRequest request) {
        return (page) -> {
            CompetitionPageRequest pageReq = CompetitionPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .onlineType(request.getOnlineType())
                    .orgId(request.getOrgId())
                    .competitionLevel(request.getCompetitionLevel())
                    .appCode(request.getAppCode())
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .competitionEnabled(request.getCompetitionEnabled())
                    .keyword(request.getKeyword())
                    .acayearSemCode(request.getAcayearSemCode())
                    .orgType(request.getOrgType())
                    .terminalFlag(request.getTerminalFlag())
                    .accountId(request.getAccountId())
                    .sortField(request.getSortField())
                    .build();
            return BaseFetchUtil.scheduleResponse(businessService.getCompetitionPage(pageReq));
        };
    }

    public Function<CompetitionPageRequest, BasePageData<CompetitionData>> createGetCompetitionPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getCompetitionPage(request));
    }

    public Function<Long, BasePageData<SportMeetData>> createGetSportMeetPagesAllFunc(SportMeetPageRequest request) {
        return (page) -> {
            SportMeetPageRequest pageReq = SportMeetPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .appCode(request.getAppCode())
                    .classIds(request.getClassIds())
                    .orgId(request.getOrgId())
                    .state(request.getState())
                    .keyword(request.getKeyword())
                    .id(request.getId())
                    .showGroup(request.getShowGroup())
                    .checkTime(request.getCheckTime())
                    .orderByField(request.getOrderByField())
                    .lastUpdateTime(request.getLastUpdateTime())
                    .build();
            return BaseFetchUtil.scheduleResponse(businessService.getSportMeetPage(pageReq));
        };
    }

    public Function<SportMeetPageRequest, BasePageData<SportMeetData>> createGetSportMeetPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getSportMeetPage(request));
    }

    public Function<DownloadGroupTeamPageRequest, List<GroupTeamData>> createDownloadGroupTeamPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.downloadGroupTeamPage(request));
    }

    public Function<DownloadGroupTeamDetailPageRequest, List<GroupTeamDetailData>> createDownloadGroupTeamDetailPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.downloadGroupTeamDetailPage(request));
    }

    public Function<ResTypeListRequest, List<ResTypeData>> createGetResTypeListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getResTypeList(request));
    }

    public Function<ResPageRequest, BasePageData<ResData>> createGetResPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getResPage(request));
    }

    public Function<ResInfoRequest, ResData> createGetResInfoFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(businessService.getResInfo(request));
    }
}
