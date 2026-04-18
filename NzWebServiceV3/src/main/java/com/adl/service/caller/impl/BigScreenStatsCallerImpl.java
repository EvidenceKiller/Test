package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.BigScreenStatsCaller;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.http.request.BestRankTopRequest;
import com.adl.service.http.request.CompetitionRankRequest;
import com.adl.service.http.request.MorePeopleRecordRankRequest;
import com.adl.service.http.request.MorePeopleVictoryRankRequest;
import com.adl.service.http.request.OverviewRequest;
import com.adl.service.http.request.PersonRankRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.SportRankRequest;
import com.adl.service.http.request.SumScoreRankRequest;
import com.adl.service.http.request.WarRecordRankRequest;
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
import com.adl.service.web.BigScreenStatsService;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * BigScreenStatsCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class BigScreenStatsCallerImpl implements BigScreenStatsCaller {

    private final BigScreenStatsService bigScreenStatsService;

    public BigScreenStatsCallerImpl(BigScreenStatsService bigScreenStatsService) {
        this.bigScreenStatsService = bigScreenStatsService;
    }

    @Override
    public long getWarRecordRankAsync(RequestScope scope, WarRecordRankRequest request, RequestCallback<List<WarRecordRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getWarRecordRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<WarRecordRankData> getWarRecordRankSync(WarRecordRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getWarRecordRank(request));
    }

    @Override
    public long getPersonRankAsync(RequestScope scope, PersonRankRequest request, RequestCallback<PersonRankData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getPersonRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public PersonRankData getPersonRankSync(PersonRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getPersonRank(request));
    }

    @Override
    public long getMorePeopleVictoryRankAsync(RequestScope scope, MorePeopleVictoryRankRequest request, RequestCallback<List<MorePeopleVictoryRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getMorePeopleVictoryRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<MorePeopleVictoryRankData> getMorePeopleVictoryRankSync(MorePeopleVictoryRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getMorePeopleVictoryRank(request));
    }

    @Override
    public long getMorePeopleRecordRankAsync(RequestScope scope, MorePeopleRecordRankRequest request, RequestCallback<List<MorePeopleRecordRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getMorePeopleRecordRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<MorePeopleRecordRankData> getMorePeopleRecordRankSync(MorePeopleRecordRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getMorePeopleRecordRank(request));
    }

    @Override
    public long getOverviewAsync(RequestScope scope, OverviewRequest request, RequestCallback<OverviewData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getOverview(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public OverviewData getOverviewSync(OverviewRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getOverview(request));
    }

    @Override
    public long getCompetitionRankAsync(RequestScope scope, CompetitionRankRequest request, RequestCallback<CompetitionRankData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getCompetitionRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public CompetitionRankData getCompetitionRankSync(CompetitionRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getCompetitionRank(request));
    }

    @Override
    public long sumScoreRankAsync(RequestScope scope, SumScoreRankRequest request, RequestCallback<List<SumScoreRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.sumScoreRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SumScoreRankData> sumScoreRankSync(SumScoreRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.sumScoreRank(request));
    }

    @Override
    public long getSportSkuRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportSkuRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getSportSkuRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportSkuRankData> getSportSkuRankSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getSportSkuRank(request));
    }

    @Override
    public long getSportRankKingAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getSportRankKing(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportRankKingData> getSportRankKingSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getSportRankKing(request));
    }

    @Override
    public long getPhysicalTrainingRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getPhysicalTrainingRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportRankKingData> getPhysicalTrainingRankSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getPhysicalTrainingRank(request));
    }

    @Override
    public long getExerciseSumTimeRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getExerciseSumTimeRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportRankKingData> getExerciseSumTimeRankSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getExerciseSumTimeRank(request));
    }

    @Override
    public long getBestRankTopAsync(RequestScope scope, BestRankTopRequest request, RequestCallback<List<BestRankTopData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(bigScreenStatsService.getBestRankTop(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<BestRankTopData> getBestRankTopSync(BestRankTopRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(bigScreenStatsService.getBestRankTop(request));
    }
}
