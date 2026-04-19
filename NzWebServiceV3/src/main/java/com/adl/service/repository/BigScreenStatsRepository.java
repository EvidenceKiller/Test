package com.adl.service.repository;

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
import com.adl.service.repository.fetch.BigScreenStatsFetchHelper;
import com.adl.service.repository.persist.BigScreenStatsPersistHelper;
import com.adl.service.repository.prepare.BigScreenStatsPrepareHelper;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public final class BigScreenStatsRepository {
    private final BigScreenStatsPrepareHelper prepareHelper = new BigScreenStatsPrepareHelper();
    private final BigScreenStatsFetchHelper fetchHelper = new BigScreenStatsFetchHelper();
    private final BigScreenStatsPersistHelper persistHelper = new BigScreenStatsPersistHelper();

    public BigScreenStatsRepository() {
    }

    public Single<List<WarRecordRankData>> getWarRecordRank(WarRecordRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetWarRecordRankFunc());
    }

    public Single<PersonRankData> getPersonRank(PersonRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetPersonRankFunc());
    }

    public Single<List<MorePeopleVictoryRankData>> getMorePeopleVictoryRank(MorePeopleVictoryRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetMorePeopleVictoryRankFunc());
    }

    public Single<List<MorePeopleRecordRankData>> getMorePeopleRecordRank(MorePeopleRecordRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetMorePeopleRecordRankFunc());
    }

    public Single<OverviewData> getOverview(OverviewRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetOverviewFunc());
    }

    public Single<CompetitionRankData> getCompetitionRank(CompetitionRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetCompetitionRankFunc());
    }

    public Single<List<SumScoreRankData>> sumScoreRank(SumScoreRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createSumScoreRankFunc());
    }

    public Single<List<SportSkuRankData>> getSportSkuRank(SportRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetSportSkuRankFunc());
    }

    public Single<List<SportRankKingData>> getSportRankKing(SportRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetSportRankKingFunc());
    }

    public Single<List<SportRankKingData>> getPhysicalTrainingRank(SportRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetPhysicalTrainingRankFunc());
    }

    public Single<List<SportRankKingData>> getExerciseSumTimeRank(SportRankRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetExerciseSumTimeRankFunc());
    }

    public Single<List<BestRankTopData>> getBestRankTop(BestRankTopRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetBestRankTopFunc());
    }
}
