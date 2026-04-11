package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.adl.service.db.converter.ListSportMeetingDetailConverter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "_meet_group_team")
@TypeConverters(value = {ListSportMeetingDetailConverter.class})
public class MeetGroupTeamEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 应用编码【手动添加】
    @ColumnInfo(name = "_app_code")
    private String appCode;

    //  运动会id【手动添加】
    @ColumnInfo(name = "_meet_id")
    private String meetId;

    //  分组code
    @ColumnInfo(name = "_team_unit_code")
    private String teamUnitCode;

    //  分组名称
    @ColumnInfo(name = "_team_unit_name")
    private String teamUnitName;

    //  项目名称
    @ColumnInfo(name = "_sport_sku_name")
    private String sportSkuName;

    //  项目skuId
    @ColumnInfo(name = "_sport_sku_id")
    private String sportSkuId;

    //  运动会项目skuId
    @ColumnInfo(name = "_meet_sport_skuId")
    private String meetSportSkuId;

    //  组数
    @ColumnInfo(name = "_team_num")
    private int teamNum;

    //  人数
    @ColumnInfo(name = "_number")
    private int number;

    //  比赛状态 0未完赛 1已完赛
    @ColumnInfo(name = "_competition_status")
    private int competitionStatus;

    //  tis_competition_meet_type  1集体 2个人
    @ColumnInfo(name = "_competition_type")
    private int competitionType;

    //  轮次
    @ColumnInfo(name = "_competition_round")
    private int competitionRound;

    //  人员详情
    @ColumnInfo(name = "_details")
    private List<MeetGroupDetailsEntity> details;

    //  手动数据
    public void setManualData(String appCode, String meetId) {
        this.appCode = appCode;
        this.meetId = meetId;
    }

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getTeamUnitCode() {
        return teamUnitCode;
    }

    public void setTeamUnitCode(String teamUnitCode) {
        this.teamUnitCode = teamUnitCode;
    }

    public String getTeamUnitName() {
        return teamUnitName;
    }

    public void setTeamUnitName(String teamUnitName) {
        this.teamUnitName = teamUnitName;
    }

    public String getMeetId() {
        return meetId;
    }

    public void setMeetId(String meetId) {
        this.meetId = meetId;
    }

    public String getSportSkuName() {
        return sportSkuName;
    }

    public void setSportSkuName(String sportSkuName) {
        this.sportSkuName = sportSkuName;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

    public String getMeetSportSkuId() {
        return meetSportSkuId;
    }

    public void setMeetSportSkuId(String meetSportSkuId) {
        this.meetSportSkuId = meetSportSkuId;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCompetitionStatus() {
        return competitionStatus;
    }

    public void setCompetitionStatus(int competitionStatus) {
        this.competitionStatus = competitionStatus;
    }

    public int getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(int competitionType) {
        this.competitionType = competitionType;
    }

    public int getCompetitionRound() {
        return competitionRound;
    }

    public void setCompetitionRound(int competitionRound) {
        this.competitionRound = competitionRound;
    }

    public List<MeetGroupDetailsEntity> getDetails() {
        return details;
    }

    public void setDetails(List<MeetGroupDetailsEntity> details) {
        this.details = details;
    }
}
