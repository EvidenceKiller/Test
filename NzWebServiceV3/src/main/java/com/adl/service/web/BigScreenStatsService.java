package com.adl.service.web;

import com.adl.service.http.request.BestRankTopRequest;
import com.adl.service.http.request.CompetitionRankRequest;
import com.adl.service.http.request.MorePeopleRecordRankRequest;
import com.adl.service.http.request.MorePeopleVictoryRankRequest;
import com.adl.service.http.request.OverviewRequest;
import com.adl.service.http.request.PersonRankRequest;
import com.adl.service.http.request.SportRankRequest;
import com.adl.service.http.request.SumScoreRankRequest;
import com.adl.service.http.request.WarRecordRankRequest;
import com.adl.service.data.BaseResponse;
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

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API（v3new 生成）
 */
public interface BigScreenStatsService {
    /**
     * 双人对战排行榜
     */
    @POST("tis/device/statistics/account/sport/warRecordRank")
    BaseResponse<List<WarRecordRankData>> getWarRecordRank(@Body WarRecordRankRequest request);

    /**
     * 个人运动排名
     */
    @POST("tis/device/statistics/account/sport/personRank")
    BaseResponse<PersonRankData> getPersonRank(@Body PersonRankRequest request);

    /**
     * 多人对战胜利榜
     */
    @POST("tis/device/statistics/account/sport/morePeopleVictoryRank")
    BaseResponse<List<MorePeopleVictoryRankData>> getMorePeopleVictoryRank(@Body MorePeopleVictoryRankRequest request);

    /**
     * 多人对战排行榜
     */
    @POST("tis/device/statistics/account/sport/morePeopleRecordRank")
    BaseResponse<List<MorePeopleRecordRankData>> getMorePeopleRecordRank(@Body MorePeopleRecordRankRequest request);

    /**
     * 总览数据
     */
    @POST("tis/device/statistics/home/sport/overview")
    BaseResponse<OverviewData> getOverview(@Body OverviewRequest request);

    /**
     * 赛事排行榜
     */
    @POST("tis/device/statistics/pco/sport/competitionRank")
    BaseResponse<CompetitionRankData> getCompetitionRank(@Body CompetitionRankRequest request);

    /**
     * 项目结果求和排名
     */
    @POST("tis/device/statistics/exercise/sport/sumScoreRank")
    BaseResponse<List<SumScoreRankData>> sumScoreRank(@Body SumScoreRankRequest request);

    /**
     * 项目热度排行
     */
    @POST("tis/device/statistics/exercise/sport/sportSkuRank")
    BaseResponse<List<SportSkuRankData>> getSportSkuRank(@Body SportRankRequest request);

    /**
     * 霸榜之王
     */
    @POST("tis/device/statistics/exercise/sport/sportRankKing")
    BaseResponse<List<SportRankKingData>> getSportRankKing(@Body SportRankRequest request);

    /**
     * 锻炼排行榜、历史最佳
     */
    @POST("tis/device/statistics/exercise/sport/physicalTrainingRank")
    BaseResponse<List<SportRankKingData>> getPhysicalTrainingRank(@Body SportRankRequest request);

    /**
     * 锻炼之星
     */
    @POST("tis/device/statistics/exercise/sport/exerciseSumTimeRank")
    BaseResponse<List<SportRankKingData>> getExerciseSumTimeRank(@Body SportRankRequest request);

    /**
     * 最佳榜单
     */
    @POST("tis/device/statistics/exercise/sport/bestRankTop")
    BaseResponse<List<BestRankTopData>> getBestRankTop(@Body BestRankTopRequest request);
}
