package com.adl.service.web;

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
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
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

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API（v3new 生成）
 */
public interface BusinessService {
    /**
     * 保存分组信息
     */
    @POST("tis/device/busi/org/class/group/save/group")
    BaseResponse<Boolean> saveGroup(@Body SaveGroupRequest request);

    /**
     * 获取分组信息
     */
    @POST("tis/device/busi/org/class/group/get/group")
    BaseResponse<GroupData> getGroup(@Body GetGroupRequest request);

    /**
     * 训练计划一天的项目
     */
    @POST("tis/device/busi/train/plan/getTrainPlanOneDayProjectList")
    BaseResponse<List<TrainPlanOneDayProjectData>> getTrainPlanOneDayProjectList(@Body TrainPlanOneDayProjectListRequest request);

    /**
     * 训练计划列表
     */
    @POST("tis/device/busi/train/plan/getTrainPlanInfoList")
    BaseResponse<BasePageData<TrainPlanInfoData>> getTrainPlanInfoPage(@Body TrainPlanInfoPageRequest request);

    /**
     * 参与竞技排名赛事
     */
    @POST("tis/device/busi/org/competition/online/joinCompetitionRank")
    BaseResponse<Boolean> joinCompetitionRank(@Body JoinCompetitionRankRequest request);

    /**
     * 获取赛事竞技排名赛详情
     */
    @POST("tis/device/busi/org/competition/online/getCompetitionRankDetail")
    BaseResponse<CompetitionRankDetailData> getCompetitionRankDetail(@Body CompetitionRankDetailRequest request);

    /**
     * 赛事列表
     */
    @POST("tis/device/busi/org/competition/online/getCompetitionList")
    BaseResponse<BasePageData<CompetitionData>> getCompetitionPage(@Body CompetitionPageRequest request);

    /**
     * 获取运动会列表
     */
    @POST("tis/device/busi/org/meet/getSportMeetList")
    BaseResponse<BasePageData<SportMeetData>> getSportMeetPage(@Body SportMeetPageRequest request);

    /**
     * 拉取编排数据
     */
    @POST("tis/device/busi/org/meet/downloadGroupTeam")
    BaseResponse<List<GroupTeamData>> downloadGroupTeamPage(@Body DownloadGroupTeamPageRequest request);

    /**
     * 拉取编排详情数据
     */
    @POST("tis/device/busi/org/meet/downloadGroupTeamDetail")
    BaseResponse<List<GroupTeamDetailData>> downloadGroupTeamDetailPage(@Body DownloadGroupTeamDetailPageRequest request);

    /**
     * 获取资源分类列表
     */
    @POST("tis/device/org/res/getResTypeList")
    BaseResponse<List<ResTypeData>> getResTypeList(@Body ResTypeListRequest request);

    /**
     * 获取资源列表
     */
    @POST("tis/device/org/res/getResList")
    BaseResponse<BasePageData<ResData>> getResPage(@Body ResPageRequest request);

    /**
     * 获取资源详情
     */
    @POST("tis/device/org/res/getResInfo")
    BaseResponse<ResData> getResInfo(@Body ResInfoRequest request);
}
