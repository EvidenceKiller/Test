package com.adl.service.entity;

import androidx.room.ColumnInfo;

import com.adl.service.common.InnerUtil;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 运动记录
 */

public class SportRecordModifyEntity {
    @ColumnInfo(name = "_id")
    private String id;
    @ColumnInfo(name = "_sport_result")
    private String sportResult;
    @ColumnInfo(name = "_sport_score")
    private String sportScore;
    @ColumnInfo(name = "_sport_level")
    private String sportLevel;
    //  业务数据状态:0（未上传）、1（已上传）、2（上传失败，后续不再上传）
    @ColumnInfo(name = "_upload_data_status")
    private int uploadDataStatus;
    //  修改状态:0（未修改）、1（修改）
    @ColumnInfo(name = "_upload_modify_status")
    private int uploadModifyStatus;
    //  多人运动Id
    @ColumnInfo(name = "_multi_person_id")
    private String multiPersonId;
    //  多人运动排名
    @ColumnInfo(name = "_multi_person_rank")
    private int multiPersonRank;
    //  业务数据上传次数
    @ColumnInfo(name = "_upload_data_fail_num", defaultValue = "0")
    private int uploadDataFailNum;

    //  上传日期【yyyy-MM-dd】
    @ColumnInfo(name = "_upload_date", defaultValue = "")
    private String uploadDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public String getSportScore() {
        return sportScore;
    }

    public void setSportScore(String sportScore) {
        this.sportScore = sportScore;
    }

    public String getSportLevel() {
        return sportLevel;
    }

    public void setSportLevel(String sportLevel) {
        this.sportLevel = sportLevel;
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

    public String getMultiPersonId() {
        return multiPersonId;
    }

    public void setMultiPersonId(String multiPersonId) {
        this.multiPersonId = multiPersonId;
    }

    public int getMultiPersonRank() {
        return multiPersonRank;
    }

    public void setMultiPersonRank(int multiPersonRank) {
        this.multiPersonRank = multiPersonRank;
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

    public void restUpdateFail() {
        this.uploadDataFailNum = 0;
        this.uploadDate = InnerUtil.formatYMDNow();
    }
}
