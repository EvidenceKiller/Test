package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.adl.service.common.InnerUtil;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * hhd
 * 2025/4/22
 */
@Entity(tableName = "_teacher_sport_local")
public class TeacherSportLocalEntity implements Serializable {

    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    @ColumnInfo(name = "_account_id")

    private String accountId;

    @ColumnInfo(name = "_belong_id")
    private String belongId;
    @ColumnInfo(name = "_app_code")
    private String appCode;
    @ColumnInfo(name = "_app_name")
    private String appName;

    @ColumnInfo(name = "_account_name")
    private String accountName;
    @ColumnInfo(name = "_bravo_count")
    private int bravoCount;
    @ColumnInfo(name = "_extra_json")
    private String extraJson;
    @ColumnInfo(name = "_good_count")
    private int goodCount;
    @ColumnInfo(name = "_sport_end_time")
    private String sportEndTime;

    @ColumnInfo(name = "_sport_start_time")
    private String sportStartTime;

    @ColumnInfo(name = "_sport_sku_id")
    private String sportSkuId;

    @ColumnInfo(name = "_sport_status")
    private String sportTime;

    @ColumnInfo(name = "_sport_count")
    private String sportCount;

    @ColumnInfo(name = "_sport_score")
    private String sportScore;

    @ColumnInfo(name = "_sport_result")
    private String sportResult;

    @ColumnInfo(name = "_sport_violation_status")
    private String sportViolationStatus;

    @ColumnInfo(name = "_sport_scene_code")
    private String sportSceneCode;

    //  业务数据状态:0（未上传）、1（已上传）、2（上传失败，后续不再上传）
    @ColumnInfo(name = "_upload_data_status")
    private int uploadDataStatus;

    //  修改状态:0（未修改）、1（修改）
    @ColumnInfo(name = "_upload_modify_status", defaultValue = "0")
    private int uploadModifyStatus;

    //  业务数据上传次数
    @ColumnInfo(name = "_upload_data_fail_num", defaultValue = "0")
    private int uploadDataFailNum;

    //  上传日期【yyyy-MM-dd】
    @ColumnInfo(name = "_upload_date", defaultValue = "")
    private String uploadDate;

    @ColumnInfo(name = "_device_id", defaultValue = "")
    private String deviceId;

    @Ignore
    private String originalSportResult;

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBelongId() {
        return belongId;
    }

    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getBravoCount() {
        return bravoCount;
    }

    public void setBravoCount(int bravoCount) {
        this.bravoCount = bravoCount;
    }

    public String getExtraJson() {
        return extraJson;
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public String getSportEndTime() {
        return sportEndTime;
    }

    public void setSportEndTime(String sportEndTime) {
        this.sportEndTime = sportEndTime;
    }

    public String getSportStartTime() {
        return sportStartTime;
    }

    public void setSportStartTime(String sportStartTime) {
        this.sportStartTime = sportStartTime;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

    public String getSportTime() {
        return sportTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }

    public String getSportViolationStatus() {
        return sportViolationStatus;
    }

    public void setSportViolationStatus(String sportViolationStatus) {
        this.sportViolationStatus = sportViolationStatus;
    }

    public int getUploadDataStatus() {
        return uploadDataStatus;
    }

    public void setUploadDataStatus(int uploadDataStatus) {
        this.uploadDataStatus = uploadDataStatus;
    }

    public int getUploadModifyStatus() {
        return uploadModifyStatus;
    }

    public void setUploadModifyStatus(int uploadModifyStatus) {
        this.uploadModifyStatus = uploadModifyStatus;
    }

    public int getUploadDataFailNum() {
        return uploadDataFailNum;
    }

    public void setUploadDataFailNum(int uploadDataFailNum) {
        this.uploadDataFailNum = uploadDataFailNum;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSportCount() {
        return sportCount;
    }

    public void setSportCount(String sportCount) {
        this.sportCount = sportCount;
    }

    public String getSportScore() {
        return sportScore;
    }

    public void setSportScore(String sportScore) {
        this.sportScore = sportScore;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public String getSportSceneCode() {
        return sportSceneCode;
    }

    public void setSportSceneCode(String sportSceneCode) {
        this.sportSceneCode = sportSceneCode;
    }

    public void setTodayUploadDate() {
        this.uploadDate = InnerUtil.formatYMDNow();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isTodayUploadDate() {
        return InnerUtil.formatYMDNow().equals(this.uploadDate);
    }

    public void addUploadDataFailNum() {
        this.uploadDataFailNum += 1;
    }

    public void restUploadDataFailNum() {
        this.uploadDataFailNum = 0;
    }

    public String getOriginalSportResult() {
        return originalSportResult;
    }

    public void setOriginalSportResult(String originalSportResult) {
        this.originalSportResult = originalSportResult;
    }
}
