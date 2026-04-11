package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class MeetSkuEntity implements Serializable {
    //  运动会运动sku标识码
    private String id;
    //  运动会标识码
    private String meetId;
    //  运动sku标识码
    private String sportSkuId;
    //  运动sku标识码
    private String sportSkuName;
    //  类型名称
    private String sportTypeName;

    //  精度方式 详见码表
    private int accuracyMethod;
    //  精度类型 详见码表
    private int accuracyType;
    //  报名人数
    private int applyUserCount;
    //  比赛方式
    private int competitionType;

    //  封面地址
    private String coverUrl;
    //  结束类型 参考字典编码：adl_bms_sport_sku_end_type
    private int endType;
    //  结束方式 参考字典编码：adl_sport_end_unit
    private int endValue;
    //  结束方式为其他时的备注
    private String endRemark;
    //  测量方式 参考字典编码：adl_bms_sport_sku_measure_mode
    private String measureMode;
    //  测量单位代码 参考字典编码：adl_sport_sport_unit
    private String measureUnitCode;

    //  排序 低优策略
    private int meetSkuSort;
    //  项目设置id
    private String settingId;
    //  项目高低优 参考字典编码：adl_bms_sport_priority
    private int sportPriority;

    //  测试人数
    private int testCount;

    //
    private int competitionNum;

    private List<String> appCodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetId() {
        return meetId;
    }

    public void setMeetId(String meetId) {
        this.meetId = meetId;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

    public String getSportSkuName() {
        return sportSkuName;
    }

    public void setSportSkuName(String sportSkuName) {
        this.sportSkuName = sportSkuName;
    }

    public String getSportTypeName() {
        return sportTypeName;
    }

    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }

    public int getAccuracyMethod() {
        return accuracyMethod;
    }

    public void setAccuracyMethod(int accuracyMethod) {
        this.accuracyMethod = accuracyMethod;
    }

    public int getAccuracyType() {
        return accuracyType;
    }

    public void setAccuracyType(int accuracyType) {
        this.accuracyType = accuracyType;
    }

    public int getApplyUserCount() {
        return applyUserCount;
    }

    public void setApplyUserCount(int applyUserCount) {
        this.applyUserCount = applyUserCount;
    }

    public int getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(int competitionType) {
        this.competitionType = competitionType;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getEndType() {
        return endType;
    }

    public void setEndType(int endType) {
        this.endType = endType;
    }

    public int getEndValue() {
        return endValue;
    }

    public void setEndValue(int endValue) {
        this.endValue = endValue;
    }

    public String getEndRemark() {
        return endRemark;
    }

    public void setEndRemark(String endRemark) {
        this.endRemark = endRemark;
    }

    public String getMeasureMode() {
        return measureMode;
    }

    public void setMeasureMode(String measureMode) {
        this.measureMode = measureMode;
    }

    public String getMeasureUnitCode() {
        return measureUnitCode;
    }

    public void setMeasureUnitCode(String measureUnitCode) {
        this.measureUnitCode = measureUnitCode;
    }

    public int getMeetSkuSort() {
        return meetSkuSort;
    }

    public void setMeetSkuSort(int meetSkuSort) {
        this.meetSkuSort = meetSkuSort;
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    public int getSportPriority() {
        return sportPriority;
    }

    public void setSportPriority(int sportPriority) {
        this.sportPriority = sportPriority;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    public int getCompetitionNum() {
        return competitionNum;
    }

    public void setCompetitionNum(int competitionNum) {
        this.competitionNum = competitionNum;
    }

    public List<String> getAppCodes() {
        return appCodes;
    }

    public void setAppCodes(List<String> appCodes) {
        this.appCodes = appCodes;
    }
}
