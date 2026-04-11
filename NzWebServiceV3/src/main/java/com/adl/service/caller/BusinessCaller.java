package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.web.request.CompetitionPageRequest;
import com.adl.service.web.request.CompetitionRankDetailRequest;
import com.adl.service.web.request.DownloadGroupTeamDetailPageRequest;
import com.adl.service.web.request.DownloadGroupTeamPageRequest;
import com.adl.service.web.request.GetGroupRequest;
import com.adl.service.web.request.JoinCompetitionRankRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.ResInfoRequest;
import com.adl.service.web.request.ResPageRequest;
import com.adl.service.web.request.ResTypeListRequest;
import com.adl.service.web.request.SaveGroupRequest;
import com.adl.service.web.request.SportMeetPageRequest;
import com.adl.service.web.request.TrainPlanInfoPageRequest;
import com.adl.service.web.request.TrainPlanOneDayProjectListRequest;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.CompetitionData;
import com.adl.service.web.response.CompetitionRankDetailData;
import com.adl.service.web.response.GroupData;
import com.adl.service.web.response.GroupTeamData;
import com.adl.service.web.response.GroupTeamDetailData;
import com.adl.service.web.response.ResData;
import com.adl.service.web.response.ResTypeData;
import com.adl.service.web.response.SportMeetData;
import com.adl.service.web.response.TrainPlanInfoData;
import com.adl.service.web.response.TrainPlanOneDayProjectData;

import java.util.List;

/**
 * BusinessCaller。
 */
public interface BusinessCaller {

    /**
     * 保存分组信息
     * <p>异步调用。</p>
     */
    long saveGroupAsync(RequestScope scope, SaveGroupRequest request, RequestCallback<Boolean> callback);

    /**
     * 保存分组信息
     * <p>同步调用。</p>
     */
    Boolean saveGroupSync(SaveGroupRequest request) throws NzBaseException;

    /**
     * 获取分组信息
     * <p>异步调用。</p>
     */
    long getGroupAsync(RequestScope scope, GetGroupRequest request, RequestCallback<GroupData> callback);

    /**
     * 获取分组信息
     * <p>同步调用。</p>
     */
    GroupData getGroupSync(GetGroupRequest request) throws NzBaseException;

    /**
     * 训练计划一天的项目
     * <p>异步调用。</p>
     */
    long getTrainPlanOneDayProjectListAsync(RequestScope scope, TrainPlanOneDayProjectListRequest request, RequestCallback<List<TrainPlanOneDayProjectData>> callback);

    /**
     * 训练计划一天的项目
     * <p>同步调用。</p>
     */
    List<TrainPlanOneDayProjectData> getTrainPlanOneDayProjectListSync(TrainPlanOneDayProjectListRequest request) throws NzBaseException;

    /**
     * 训练计划列表
     * <p>异步调用。</p>
     */
    long getTrainPlanInfoPageAsync(RequestScope scope, TrainPlanInfoPageRequest request, RequestCallback<BasePageData<TrainPlanInfoData>> callback);

    /**
     * 训练计划列表
     * <p>同步调用。</p>
     */
    BasePageData<TrainPlanInfoData> getTrainPlanInfoPageSync(TrainPlanInfoPageRequest request) throws NzBaseException;

    /**
     * 参与竞技排名赛事
     * <p>异步调用。</p>
     */
    long joinCompetitionRankAsync(RequestScope scope, JoinCompetitionRankRequest request, RequestCallback<Boolean> callback);

    /**
     * 参与竞技排名赛事
     * <p>同步调用。</p>
     */
    Boolean joinCompetitionRankSync(JoinCompetitionRankRequest request) throws NzBaseException;

    /**
     * 获取赛事竞技排名赛详情
     * <p>异步调用。</p>
     */
    long getCompetitionRankDetailAsync(RequestScope scope, CompetitionRankDetailRequest request, RequestCallback<CompetitionRankDetailData> callback);

    /**
     * 获取赛事竞技排名赛详情
     * <p>同步调用。</p>
     */
    CompetitionRankDetailData getCompetitionRankDetailSync(CompetitionRankDetailRequest request) throws NzBaseException;

    /**
     * 赛事列表
     * <p>异步调用。</p>
     */
    long getCompetitionPageAsync(RequestScope scope, CompetitionPageRequest request, RequestCallback<BasePageData<CompetitionData>> callback);

    /**
     * 赛事列表
     * <p>同步调用。</p>
     */
    BasePageData<CompetitionData> getCompetitionPageSync(CompetitionPageRequest request) throws NzBaseException;

    /**
     * 获取运动会列表
     * <p>异步调用。</p>
     */
    long getSportMeetPageAsync(RequestScope scope, SportMeetPageRequest request, RequestCallback<BasePageData<SportMeetData>> callback);

    /**
     * 获取运动会列表
     * <p>同步调用。</p>
     */
    BasePageData<SportMeetData> getSportMeetPageSync(SportMeetPageRequest request) throws NzBaseException;

    /**
     * 拉取编排数据
     * <p>异步调用。</p>
     */
    long downloadGroupTeamPageAsync(RequestScope scope, DownloadGroupTeamPageRequest request, RequestCallback<List<GroupTeamData>> callback);

    /**
     * 拉取编排数据
     * <p>同步调用。</p>
     */
    List<GroupTeamData> downloadGroupTeamPageSync(DownloadGroupTeamPageRequest request) throws NzBaseException;

    /**
     * 拉取编排详情数据
     * <p>异步调用。</p>
     */
    long downloadGroupTeamDetailPageAsync(RequestScope scope, DownloadGroupTeamDetailPageRequest request, RequestCallback<List<GroupTeamDetailData>> callback);

    /**
     * 拉取编排详情数据
     * <p>同步调用。</p>
     */
    List<GroupTeamDetailData> downloadGroupTeamDetailPageSync(DownloadGroupTeamDetailPageRequest request) throws NzBaseException;

    /**
     * 获取资源分类列表
     * <p>异步调用。</p>
     */
    long getResTypeListAsync(RequestScope scope, ResTypeListRequest request, RequestCallback<List<ResTypeData>> callback);

    /**
     * 获取资源分类列表
     * <p>同步调用。</p>
     */
    List<ResTypeData> getResTypeListSync(ResTypeListRequest request) throws NzBaseException;

    /**
     * 获取资源列表
     * <p>异步调用。</p>
     */
    long getResPageAsync(RequestScope scope, ResPageRequest request, RequestCallback<BasePageData<ResData>> callback);

    /**
     * 获取资源列表
     * <p>同步调用。</p>
     */
    BasePageData<ResData> getResPageSync(ResPageRequest request) throws NzBaseException;

    /**
     * 获取资源详情
     * <p>异步调用。</p>
     */
    long getResInfoAsync(RequestScope scope, ResInfoRequest request, RequestCallback<ResData> callback);

    /**
     * 获取资源详情
     * <p>同步调用。</p>
     */
    ResData getResInfoSync(ResInfoRequest request) throws NzBaseException;

}
