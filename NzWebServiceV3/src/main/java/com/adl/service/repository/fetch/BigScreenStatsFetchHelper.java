package com.adl.service.repository.fetch;

import com.adl.service.data.BestRankTopData;
import com.adl.service.data.CompetitionRankData;
import com.adl.service.data.MorePeopleRecordRankData;
import com.adl.service.data.MorePeopleVictoryRankData;
import com.adl.service.data.OverviewData;
import com.adl.service.data.PersonRankData;
import com.adl.service.data.SportRankKingData;
import com.adl.service.data.SportSkuRankData;
import com.adl.service.data.SumScoreRankData;
import com.adl.service.data.WarRecordRankData;
import com.adl.service.http.request.BestRankTopRequest;
import com.adl.service.http.request.CompetitionRankRequest;
import com.adl.service.http.request.MorePeopleRecordRankRequest;
import com.adl.service.http.request.MorePeopleVictoryRankRequest;
import com.adl.service.http.request.OverviewRequest;
import com.adl.service.http.request.PersonRankRequest;
import com.adl.service.http.request.SportRankRequest;
import com.adl.service.http.request.SumScoreRankRequest;
import com.adl.service.http.request.WarRecordRankRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.BigScreenStatsService;

import java.util.List;

import io.reactivex.rxjava3.functions.Function;

public final class BigScreenStatsFetchHelper {

    private final BigScreenStatsService bigScreenStatsService;

    public BigScreenStatsFetchHelper() {
        this.bigScreenStatsService = RetrofitManager.getInstance().create(BigScreenStatsService.class);
    }

    public Function<WarRecordRankRequest, List<WarRecordRankData>> createGetWarRecordRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getWarRecordRank(request));
    }

    public Function<PersonRankRequest, PersonRankData> createGetPersonRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getPersonRank(request));
    }

    public Function<MorePeopleVictoryRankRequest, List<MorePeopleVictoryRankData>> createGetMorePeopleVictoryRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getMorePeopleVictoryRank(request));
    }

    public Function<MorePeopleRecordRankRequest, List<MorePeopleRecordRankData>> createGetMorePeopleRecordRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getMorePeopleRecordRank(request));
    }

    public Function<OverviewRequest, OverviewData> createGetOverviewFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getOverview(request));
    }

    public Function<CompetitionRankRequest, CompetitionRankData> createGetCompetitionRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getCompetitionRank(request));
    }

    public Function<SumScoreRankRequest, List<SumScoreRankData>> createSumScoreRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.sumScoreRank(request));
    }

    public Function<SportRankRequest, List<SportSkuRankData>> createGetSportSkuRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getSportSkuRank(request));
    }

    public Function<SportRankRequest, List<SportRankKingData>> createGetSportRankKingFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getSportRankKing(request));
    }

    public Function<SportRankRequest, List<SportRankKingData>> createGetPhysicalTrainingRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getPhysicalTrainingRank(request));
    }

    public Function<SportRankRequest, List<SportRankKingData>> createGetExerciseSumTimeRankFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getExerciseSumTimeRank(request));
    }

    public Function<BestRankTopRequest, List<BestRankTopData>> createGetBestRankTopFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(bigScreenStatsService.getBestRankTop(request));
    }
}
