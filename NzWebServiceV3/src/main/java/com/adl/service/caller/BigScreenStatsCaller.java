package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.web.request.BestRankTopRequest;
import com.adl.service.web.request.CompetitionRankRequest;
import com.adl.service.web.request.MorePeopleRecordRankRequest;
import com.adl.service.web.request.MorePeopleVictoryRankRequest;
import com.adl.service.web.request.OverviewRequest;
import com.adl.service.web.request.PersonRankRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.SportRankRequest;
import com.adl.service.web.request.SumScoreRankRequest;
import com.adl.service.web.request.WarRecordRankRequest;
import com.adl.service.web.response.BestRankTopData;
import com.adl.service.web.response.CompetitionRankData;
import com.adl.service.web.response.MorePeopleRecordRankData;
import com.adl.service.web.response.MorePeopleVictoryRankData;
import com.adl.service.web.response.OverviewData;
import com.adl.service.web.response.PersonRankData;
import com.adl.service.web.response.SportRankKingData;
import com.adl.service.web.response.SportSkuRankData;
import com.adl.service.web.response.SumScoreRankData;
import com.adl.service.web.response.WarRecordRankData;

import java.util.List;

/**
 * BigScreenStatsCaller。
 */
public interface BigScreenStatsCaller {

    /**
     * 双人对战排行榜
     * <p>异步调用。</p>
     */
    long getWarRecordRankAsync(RequestScope scope, WarRecordRankRequest request, RequestCallback<List<WarRecordRankData>> callback);

    /**
     * 双人对战排行榜
     * <p>同步调用。</p>
     */
    List<WarRecordRankData> getWarRecordRankSync(WarRecordRankRequest request) throws NzBaseException;

    /**
     * 个人运动排名
     * <p>异步调用。</p>
     */
    long getPersonRankAsync(RequestScope scope, PersonRankRequest request, RequestCallback<PersonRankData> callback);

    /**
     * 个人运动排名
     * <p>同步调用。</p>
     */
    PersonRankData getPersonRankSync(PersonRankRequest request) throws NzBaseException;

    /**
     * 多人对战胜利榜
     * <p>异步调用。</p>
     */
    long getMorePeopleVictoryRankAsync(RequestScope scope, MorePeopleVictoryRankRequest request, RequestCallback<List<MorePeopleVictoryRankData>> callback);

    /**
     * 多人对战胜利榜
     * <p>同步调用。</p>
     */
    List<MorePeopleVictoryRankData> getMorePeopleVictoryRankSync(MorePeopleVictoryRankRequest request) throws NzBaseException;

    /**
     * 多人对战排行榜
     * <p>异步调用。</p>
     */
    long getMorePeopleRecordRankAsync(RequestScope scope, MorePeopleRecordRankRequest request, RequestCallback<List<MorePeopleRecordRankData>> callback);

    /**
     * 多人对战排行榜
     * <p>同步调用。</p>
     */
    List<MorePeopleRecordRankData> getMorePeopleRecordRankSync(MorePeopleRecordRankRequest request) throws NzBaseException;

    /**
     * 总览数据
     * <p>异步调用。</p>
     */
    long getOverviewAsync(RequestScope scope, OverviewRequest request, RequestCallback<OverviewData> callback);

    /**
     * 总览数据
     * <p>同步调用。</p>
     */
    OverviewData getOverviewSync(OverviewRequest request) throws NzBaseException;

    /**
     * 赛事排行榜
     * <p>异步调用。</p>
     */
    long getCompetitionRankAsync(RequestScope scope, CompetitionRankRequest request, RequestCallback<CompetitionRankData> callback);

    /**
     * 赛事排行榜
     * <p>同步调用。</p>
     */
    CompetitionRankData getCompetitionRankSync(CompetitionRankRequest request) throws NzBaseException;

    /**
     * 项目结果求和排名
     * <p>异步调用。</p>
     */
    long sumScoreRankAsync(RequestScope scope, SumScoreRankRequest request, RequestCallback<List<SumScoreRankData>> callback);

    /**
     * 项目结果求和排名
     * <p>同步调用。</p>
     */
    List<SumScoreRankData> sumScoreRankSync(SumScoreRankRequest request) throws NzBaseException;

    /**
     * 项目热度排行
     * <p>异步调用。</p>
     */
    long getSportSkuRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportSkuRankData>> callback);

    /**
     * 项目热度排行
     * <p>同步调用。</p>
     */
    List<SportSkuRankData> getSportSkuRankSync(SportRankRequest request) throws NzBaseException;

    /**
     * 霸榜之王
     * <p>异步调用。</p>
     */
    long getSportRankKingAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback);

    /**
     * 霸榜之王
     * <p>同步调用。</p>
     */
    List<SportRankKingData> getSportRankKingSync(SportRankRequest request) throws NzBaseException;

    /**
     * 锻炼排行榜、历史最佳
     * <p>异步调用。</p>
     */
    long getPhysicalTrainingRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback);

    /**
     * 锻炼排行榜、历史最佳
     * <p>同步调用。</p>
     */
    List<SportRankKingData> getPhysicalTrainingRankSync(SportRankRequest request) throws NzBaseException;

    /**
     * 锻炼之星
     * <p>异步调用。</p>
     */
    long getExerciseSumTimeRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback);

    /**
     * 锻炼之星
     * <p>同步调用。</p>
     */
    List<SportRankKingData> getExerciseSumTimeRankSync(SportRankRequest request) throws NzBaseException;

    /**
     * 最佳榜单
     * <p>异步调用。</p>
     */
    long getBestRankTopAsync(RequestScope scope, BestRankTopRequest request, RequestCallback<List<BestRankTopData>> callback);

    /**
     * 最佳榜单
     * <p>同步调用。</p>
     */
    List<BestRankTopData> getBestRankTopSync(BestRankTopRequest request) throws NzBaseException;

}
