package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.BusinessCaller;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.persistence.BasePagePersistence;
import com.adl.service.persistence.BasePersistence;
import com.adl.service.persistence.BusinessPersistenceHelper;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.http.request.CompetitionPageRequest;
import com.adl.service.http.request.CompetitionRankDetailRequest;
import com.adl.service.http.request.DownloadGroupTeamDetailPageRequest;
import com.adl.service.http.request.DownloadGroupTeamPageRequest;
import com.adl.service.http.request.GetGroupRequest;
import com.adl.service.http.request.JoinCompetitionRankRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.ResInfoRequest;
import com.adl.service.http.request.ResPageRequest;
import com.adl.service.http.request.ResTypeListRequest;
import com.adl.service.http.request.SaveGroupRequest;
import com.adl.service.http.request.SportMeetPageRequest;
import com.adl.service.http.request.TrainPlanInfoPageRequest;
import com.adl.service.http.request.TrainPlanOneDayProjectListRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.CompetitionData;
import com.adl.service.data.CompetitionRankDetailData;
import com.adl.service.data.GroupData;
import com.adl.service.data.GroupTeamData;
import com.adl.service.data.GroupTeamDetailData;
import com.adl.service.callback.GetPageResult;
import com.adl.service.data.ResData;
import com.adl.service.data.ResTypeData;
import com.adl.service.data.SportMeetData;
import com.adl.service.data.TrainPlanInfoData;
import com.adl.service.data.TrainPlanOneDayProjectData;
import com.adl.service.web.BusinessService;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * BusinessCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class BusinessCallerImpl implements BusinessCaller {

    private final BusinessService businessService;

    public BusinessCallerImpl(BusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    public long saveGroupAsync(RequestScope scope, SaveGroupRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.saveGroup(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean saveGroupSync(SaveGroupRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.saveGroup(request));
    }

    @Override
    public long getGroupAsync(RequestScope scope, GetGroupRequest request, RequestCallback<GroupData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.getGroup(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GroupData getGroupSync(GetGroupRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.getGroup(request));
    }

    @Override
    public long getTrainPlanOneDayProjectListAsync(RequestScope scope, TrainPlanOneDayProjectListRequest request, RequestCallback<List<TrainPlanOneDayProjectData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.getTrainPlanOneDayProjectList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<TrainPlanOneDayProjectData> getTrainPlanOneDayProjectListSync(TrainPlanOneDayProjectListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.getTrainPlanOneDayProjectList(request));
    }

    @Override
    public long getTrainPlanInfoPageAsync(RequestScope scope, TrainPlanInfoPageRequest request, RequestCallback<BasePageData<TrainPlanInfoData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.getTrainPlanInfoPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<TrainPlanInfoData> getTrainPlanInfoPageSync(TrainPlanInfoPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.getTrainPlanInfoPage(request));
    }

    @Override
    public long joinCompetitionRankAsync(RequestScope scope, JoinCompetitionRankRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.joinCompetitionRank(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean joinCompetitionRankSync(JoinCompetitionRankRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.joinCompetitionRank(request));
    }

    @Override
    public long getCompetitionRankDetailAsync(RequestScope scope, CompetitionRankDetailRequest request, RequestCallback<CompetitionRankDetailData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.getCompetitionRankDetail(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public CompetitionRankDetailData getCompetitionRankDetailSync(CompetitionRankDetailRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.getCompetitionRankDetail(request));
    }

    @Override
    public long getCompetitionPagesAllAsync(RequestScope scope, CompetitionPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(BasePagePersistence.persistAllPagesAsSingle(
                BusinessPersistenceHelper.createCompetitionPagePreparer(),
                BusinessPersistenceHelper.createCompetitionPageRequester(request, req -> businessService.getCompetitionPage(req)),
                BusinessPersistenceHelper.createCompetitionPagePersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getCompetitionPagesAllSync(CompetitionPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(BasePagePersistence.persistAllPagesAsSingle(
                BusinessPersistenceHelper.createCompetitionPagePreparer(),
                BusinessPersistenceHelper.createCompetitionPageRequester(request, req -> businessService.getCompetitionPage(req)),
                BusinessPersistenceHelper.createCompetitionPagePersister()));
    }

    @Override
    public long getCompetitionPageAsync(RequestScope scope, CompetitionPageRequest request, RequestCallback<BasePageData<CompetitionData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(request, req -> businessService.getCompetitionPage(req)),
                BusinessPersistenceHelper.createCompetitionPagePersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<CompetitionData> getCompetitionPageSync(CompetitionPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(request, req -> businessService.getCompetitionPage(req)),
                BusinessPersistenceHelper.createCompetitionPagePersister()));
    }

    @Override
    public void clearSportMeetDataSync() {
        DaoManagerProxy.getInstance().getSportMeetDao().clearAll();
        DaoManagerProxy.getInstance().getSportMeetGroupTeamDao().clearAll();
        DaoManagerProxy.getInstance().getSportMeetGroupDetailsDao().clearAll();
    }

    @Override
    public long getSportMeetPagesAllAsync(RequestScope scope, SportMeetPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(BasePagePersistence.persistAllPagesAsSingle(
                BusinessPersistenceHelper.createSportMeetPagePreparer(request.getAppCode()),
                BusinessPersistenceHelper.createSportMeetPageRequester(request, req -> businessService.getSportMeetPage(req)),
                BusinessPersistenceHelper.createSportMeetPagePersister(request.getAppCode())), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getSportMeetPagesAllSync(SportMeetPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(BasePagePersistence.persistAllPagesAsSingle(
                BusinessPersistenceHelper.createSportMeetPagePreparer(request.getAppCode()),
                BusinessPersistenceHelper.createSportMeetPageRequester(request, req -> businessService.getSportMeetPage(req)),
                BusinessPersistenceHelper.createSportMeetPagePersister(request.getAppCode())));
    }

    @Override
    public long getSportMeetPageAsync(RequestScope scope, SportMeetPageRequest request, RequestCallback<BasePageData<SportMeetData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(request, req -> businessService.getSportMeetPage(request)),
                BusinessPersistenceHelper.createSportMeetPagePersister(request.getAppCode())), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<SportMeetData> getSportMeetPageSync(SportMeetPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(request, req -> businessService.getSportMeetPage(request)),
                BusinessPersistenceHelper.createSportMeetPagePersister(request.getAppCode())));
    }

    @Override
    public long downloadGroupTeamPageAsync(RequestScope scope, DownloadGroupTeamPageRequest request, RequestCallback<List<GroupTeamData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.downloadGroupTeamPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<GroupTeamData> downloadGroupTeamPageSync(DownloadGroupTeamPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.downloadGroupTeamPage(request));
    }

    @Override
    public long downloadGroupTeamDetailPageAsync(RequestScope scope, DownloadGroupTeamDetailPageRequest request, RequestCallback<List<GroupTeamDetailData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.downloadGroupTeamDetailPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<GroupTeamDetailData> downloadGroupTeamDetailPageSync(DownloadGroupTeamDetailPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.downloadGroupTeamDetailPage(request));
    }

    @Override
    public long getResTypeListAsync(RequestScope scope, ResTypeListRequest request, RequestCallback<List<ResTypeData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.getResTypeList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<ResTypeData> getResTypeListSync(ResTypeListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.getResTypeList(request));
    }

    @Override
    public long getResPageAsync(RequestScope scope, ResPageRequest request, RequestCallback<BasePageData<ResData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.getResPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<ResData> getResPageSync(ResPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.getResPage(request));
    }

    @Override
    public long getResInfoAsync(RequestScope scope, ResInfoRequest request, RequestCallback<ResData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(businessService.getResInfo(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public ResData getResInfoSync(ResInfoRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(businessService.getResInfo(request));
    }
}
