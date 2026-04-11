package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.adl.service.db.converter.ListSportMeetingGroupConverter;
import com.adl.service.db.converter.ListSportMeetingSkuConverter;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 运动会
 */
@Entity(tableName = "_sport_meet")
@TypeConverters(value = {ListSportMeetingSkuConverter.class, ListSportMeetingGroupConverter.class})
public class SportMeetingEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    //  运动会标识码
    @ColumnInfo(name = "_id")
    private String id;

    // 应用编码【手动添加】
    @ColumnInfo(name = "_app_code")
    private String appCode;

    //  运动会名称
    @ColumnInfo(name = "_meet_title")
    private String meetTitle;

    //  开始时间
    @ColumnInfo(name = "_start_time")
    private long startTime;

    //  结束时间
    @ColumnInfo(name = "_end_time")
    private long endTime;

    // 1未开始 2进行中 3已结束
    @ColumnInfo(name = "_state")
    private int state;

    //  报名开始时间
    @ColumnInfo(name = "_apply_start_time")
    private long applyStartTime;

    //  报名结束时间
    @ColumnInfo(name = "_apply_end_time")
    private long applyEndTime;

    //  创建者账号
    @ColumnInfo(name = "_creator_num")
    private String creatorNum;

    //  赛事文件地址
    @ColumnInfo(name = "_file_url")
    private String fileUrl;

    //  赛事文件名
    @ColumnInfo(name = "_file_name")
    private String fileName;

    //  应用端，逗号隔开
    @ColumnInfo(name = "_app_codes")
    private String appCodes;

    //  逻辑删除标识位 0 删除 1 可用
    @ColumnInfo(name = "_del_flag")
    private boolean delFlag;

    @ColumnInfo(name = "_create_time")
    private long createTime;

    @ColumnInfo(name = "_update_time")
    private long updateTime;

    @ColumnInfo(name = "_sku_list")
    private List<MeetSkuEntity> skuList;

    //  报名状态
    @ColumnInfo(name = "_apply_state")
    private int applyState;

    //  报名人数
    @ColumnInfo(name = "_apply_user_count")
    private int applyUserCount;

    //  比赛人数
    @ColumnInfo(name = "_players_num")
    private int playersNum;

    //  分组编排
    @ColumnInfo(name = "_group_teams")
    private List<MeetGroupTeamEntity> groupTeams;

    //  副标题
    @ColumnInfo(name = "_meet_sub_title", defaultValue = "")
    private String meetSubtitle;

    //  封面
    @ColumnInfo(name = "_meet_cover", defaultValue = "")
    private String meetCover;

    //  手动数据
    public void setManualData(String appCode) {
        this.appCode = appCode;
    }

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(long applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public long getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(long applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getCreatorNum() {
        return creatorNum;
    }

    public void setCreatorNum(String creatorNum) {
        this.creatorNum = creatorNum;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAppCodes() {
        return appCodes;
    }

    public void setAppCodes(String appCodes) {
        this.appCodes = appCodes;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public List<MeetSkuEntity> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<MeetSkuEntity> skuList) {
        this.skuList = skuList;
    }

    public int getApplyState() {
        return applyState;
    }

    public void setApplyState(int applyState) {
        this.applyState = applyState;
    }

    public int getApplyUserCount() {
        return applyUserCount;
    }

    public void setApplyUserCount(int applyUserCount) {
        this.applyUserCount = applyUserCount;
    }

    public int getPlayersNum() {
        return playersNum;
    }

    public void setPlayersNum(int playersNum) {
        this.playersNum = playersNum;
    }

    public List<MeetGroupTeamEntity> getGroupTeams() {
        return groupTeams;
    }

    public void setGroupTeams(List<MeetGroupTeamEntity> groupTeams) {
        this.groupTeams = groupTeams;
    }

    public String getMeetSubtitle() {
        return meetSubtitle;
    }

    public void setMeetSubtitle(String meetSubtitle) {
        this.meetSubtitle = meetSubtitle;
    }

    public String getMeetCover() {
        return meetCover;
    }

    public void setMeetCover(String meetCover) {
        this.meetCover = meetCover;
    }
}
