package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.BigScreenStatsCaller;
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
import com.adl.service.exception.NzBaseException;
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
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.repository.BigScreenStatsRepository;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * BigScreenStatsCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class BigScreenStatsCallerImpl implements BigScreenStatsCaller {

    private final BigScreenStatsRepository bigScreenStatsRepository;

    public BigScreenStatsCallerImpl() {
        this.bigScreenStatsRepository = new BigScreenStatsRepository();
    }

    @Override
    public long getWarRecordRankAsync(RequestScope scope, WarRecordRankRequest request, RequestCallback<List<WarRecordRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getWarRecordRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<WarRecordRankData> getWarRecordRankSync(WarRecordRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getWarRecordRank(request));
    }

    @Override
    public long getPersonRankAsync(RequestScope scope, PersonRankRequest request, RequestCallback<PersonRankData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getPersonRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public PersonRankData getPersonRankSync(PersonRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getPersonRank(request));
    }

    @Override
    public long getMorePeopleVictoryRankAsync(RequestScope scope, MorePeopleVictoryRankRequest request, RequestCallback<List<MorePeopleVictoryRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getMorePeopleVictoryRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<MorePeopleVictoryRankData> getMorePeopleVictoryRankSync(MorePeopleVictoryRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getMorePeopleVictoryRank(request));
    }

    @Override
    public long getMorePeopleRecordRankAsync(RequestScope scope, MorePeopleRecordRankRequest request, RequestCallback<List<MorePeopleRecordRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getMorePeopleRecordRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<MorePeopleRecordRankData> getMorePeopleRecordRankSync(MorePeopleRecordRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getMorePeopleRecordRank(request));
    }

    @Override
    public long getOverviewAsync(RequestScope scope, OverviewRequest request, RequestCallback<OverviewData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getOverview(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public OverviewData getOverviewSync(OverviewRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getOverview(request));
    }

    @Override
    public long getCompetitionRankAsync(RequestScope scope, CompetitionRankRequest request, RequestCallback<CompetitionRankData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getCompetitionRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public CompetitionRankData getCompetitionRankSync(CompetitionRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getCompetitionRank(request));
    }

    @Override
    public long sumScoreRankAsync(RequestScope scope, SumScoreRankRequest request, RequestCallback<List<SumScoreRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.sumScoreRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SumScoreRankData> sumScoreRankSync(SumScoreRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.sumScoreRank(request));
    }

    @Override
    public long getSportSkuRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportSkuRankData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getSportSkuRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportSkuRankData> getSportSkuRankSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getSportSkuRank(request));
    }

    @Override
    public long getSportRankKingAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getSportRankKing(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportRankKingData> getSportRankKingSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getSportRankKing(request));
    }

    @Override
    public long getPhysicalTrainingRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getPhysicalTrainingRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportRankKingData> getPhysicalTrainingRankSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getPhysicalTrainingRank(request));
    }

    @Override
    public long getExerciseSumTimeRankAsync(RequestScope scope, SportRankRequest request, RequestCallback<List<SportRankKingData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getExerciseSumTimeRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<SportRankKingData> getExerciseSumTimeRankSync(SportRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getExerciseSumTimeRank(request));
    }

    @Override
    public long getBestRankTopAsync(RequestScope scope, BestRankTopRequest request, RequestCallback<List<BestRankTopData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(bigScreenStatsRepository.getBestRankTop(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<BestRankTopData> getBestRankTopSync(BestRankTopRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(bigScreenStatsRepository.getBestRankTop(request));
    }
}
