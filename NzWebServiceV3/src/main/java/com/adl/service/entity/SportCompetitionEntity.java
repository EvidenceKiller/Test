package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "_sport_competition")
public class SportCompetitionEntity implements Serializable {

    //  赛事Id
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    //  赛事标题
    @ColumnInfo(name = "_online_title")
    private String onlineTitle;

    //  赛事副标题
    @ColumnInfo(name = "_online_sub_title")
    private String onlineSubtitle;

    //  赛事封面地址
    @ColumnInfo(name = "_online_cover")
    private String onlineCover;

    //  赛事类型 1-竞速赛 2-闯关赛
    @ColumnInfo(name = "_online_type")
    private int onlineType;

    //  赛事级别 1-省 2-市 3-区县 4-校级 99-其他
    @ColumnInfo(name = "_org_level")
    private int orgLevel;

    //  开始时间
    @ColumnInfo(name = "_start_time")
    private long startTime;

    //  结束时间
    @ColumnInfo(name = "_end_time")
    private long endTime;

    //  创建人
    @ColumnInfo(name = "_creator_num")
    private String creatorNum;

    //  创建时间
    @ColumnInfo(name = "_create_time")
    private long createTime;

    //  赛事状态 1-未开始 2-进行中 3-已结束
    @ColumnInfo(name = "_competition_enabled")
    private int competitionEnabled;

    @ColumnInfo(name = "_join_num")
    private long joinNum;

    public SportCompetitionEntity() {
        this.id = "";
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getOnlineTitle() {
        return onlineTitle;
    }

    public void setOnlineTitle(String onlineTitle) {
        this.onlineTitle = onlineTitle;
    }

    public String getOnlineSubtitle() {
        return onlineSubtitle;
    }

    public void setOnlineSubtitle(String onlineSubtitle) {
        this.onlineSubtitle = onlineSubtitle;
    }

    public String getOnlineCover() {
        return onlineCover;
    }

    public void setOnlineCover(String onlineCover) {
        this.onlineCover = onlineCover;
    }

    public int getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(int onlineType) {
        this.onlineType = onlineType;
    }

    public int getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(int orgLevel) {
        this.orgLevel = orgLevel;
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

    public String getCreatorNum() {
        return creatorNum;
    }

    public void setCreatorNum(String creatorNum) {
        this.creatorNum = creatorNum;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCompetitionEnabled() {
        return competitionEnabled;
    }

    public void setCompetitionEnabled(int competitionEnabled) {
        this.competitionEnabled = competitionEnabled;
    }

    public long getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(long joinNum) {
        this.joinNum = joinNum;
    }
}
