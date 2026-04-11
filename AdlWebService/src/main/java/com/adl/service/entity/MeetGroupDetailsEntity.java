package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "_meet_group_detail")
public class MeetGroupDetailsEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 应用编码【手动添加】
    @ColumnInfo(name = "_app_code")
    private String appCode;

    //  运动会id【手动添加】
    @ColumnInfo(name = "_meet_id")
    private String meetId;

    //  运动会项目skuId【手动添加】
    @ColumnInfo(name = "_meet_sport_skuId")
    private String meetSportSkuId;

    //  轮次【手动添加】
    @ColumnInfo(name = "_competition_round")
    private int competitionRound;

    //  分组code
    @ColumnInfo(name = "_team_unit_code")
    private String teamUnitCode;

    //  分组名称
    @ColumnInfo(name = "_team_unit_name")
    private String teamUnitName;

    //  组号
    @ColumnInfo(name = "_group_no")
    private int groupNo;

    //  识别类型  参考字典编码：adl_tis_competition_meet_identify_type（0跑道识别 1运动员编码 2序号识别）
    @ColumnInfo(name = "_identify_type")
    private int identifyType;

    //  跑道号
    @ColumnInfo(name = "_runway_code")
    private String runwayCode;

    //  运动员编号
    @ColumnInfo(name = "_number_code")
    private String numberCode;

    //  序号
    @ColumnInfo(name = "_serial_code")
    private String serialCode;

    //  运动员编码
    @ColumnInfo(name = "_sport_account_num")
    private String sportAccountNum;

    //  运动员姓名
    @ColumnInfo(name = "_account_name")
    private String accountName;

    //  运动员id
    @ColumnInfo(name = "_account_id")
    private String accountId;

    //  班级id
    @ColumnInfo(name = "_class_id")
    private String classId;

    //  班级名称
    @ColumnInfo(name = "_class_name")
    private String className;

    //  头像
    @ColumnInfo(name = "_account_avatar")
    private String accountAvatar;

    //  运动结果
    @ColumnInfo(name = "_sport_result")
    private String sportResult;

    //  手动数据
    public void setManualData(String appCode, String meetId, String meetSportSkuId, int competitionRound) {
        this.appCode = appCode;
        this.meetId = meetId;
        this.meetSportSkuId = meetSportSkuId;
        this.competitionRound = competitionRound;
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

    public String getMeetId() {
        return meetId;
    }

    public void setMeetId(String meetId) {
        this.meetId = meetId;
    }

    public String getMeetSportSkuId() {
        return meetSportSkuId;
    }

    public void setMeetSportSkuId(String meetSportSkuId) {
        this.meetSportSkuId = meetSportSkuId;
    }

    public int getCompetitionRound() {
        return competitionRound;
    }

    public void setCompetitionRound(int competitionRound) {
        this.competitionRound = competitionRound;
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

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(int identifyType) {
        this.identifyType = identifyType;
    }

    public String getRunwayCode() {
        return runwayCode;
    }

    public void setRunwayCode(String runwayCode) {
        this.runwayCode = runwayCode;
    }

    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getSportAccountNum() {
        return sportAccountNum;
    }

    public void setSportAccountNum(String sportAccountNum) {
        this.sportAccountNum = sportAccountNum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAccountAvatar() {
        return accountAvatar;
    }

    public void setAccountAvatar(String accountAvatar) {
        this.accountAvatar = accountAvatar;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public String getIdentifyCode() {
        String code = "";
        //  识别类型  参考字典编码：adl_tis_competition_meet_identify_type（0跑道识别 1运动员编码 2序号识别）
        switch (identifyType) {
            //  跑道识别
            case 0:
                code = runwayCode;
                break;
            //  运动员编码
            case 1:
                code = numberCode;
                break;
            //  序号识别
            case 2:
                code = serialCode;
                break;
        }

        return code;
    }
}
