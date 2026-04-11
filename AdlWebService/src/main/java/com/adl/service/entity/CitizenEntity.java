package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.adl.service.db.converter.ListFaceDataConverter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "_citizen")
@TypeConverters(value = {ListFaceDataConverter.class})
public class CitizenEntity {
    @ColumnInfo(name = "_user_id")
    private String userId;

    // 账号ID
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_account_id")
    private String accountId;

    // 账号名称
    @ColumnInfo(name = "_account_name")
    private String accountName;

    // 账号
    @ColumnInfo(name = "_account_num")
    private String accountNum;

    // 手机号
    @ColumnInfo(name = "_account_phone_num")
    private String accountPhoneNum;

    // 1男2女9未知
    @ColumnInfo(name = "_user_sex")
    private String userSex;

    // 工号
    @ColumnInfo(name = "_job_num")
    private String jobNum;

    // 头像
    @ColumnInfo(name = "_account_avatar")
    private String accountAvatar;

    //  创建时间
    @ColumnInfo(name = "_create_time")
    private String createTime;

    //  更新时间
    @ColumnInfo(name = "_update_time")
    private String updateTime;

    //  删除标识
    @ColumnInfo(name = "_del_flag")
    private boolean delFlag;

    @ColumnInfo(name = "_face_data_list", defaultValue = "")
    private List<FaceDataEntity> faceDataList;

    public CitizenEntity() {
        this.accountId = "";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NotNull
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(@NotNull String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountPhoneNum() {
        return accountPhoneNum;
    }

    public void setAccountPhoneNum(String accountPhoneNum) {
        this.accountPhoneNum = accountPhoneNum;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getAccountAvatar() {
        return accountAvatar;
    }

    public void setAccountAvatar(String accountAvatar) {
        this.accountAvatar = accountAvatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public List<FaceDataEntity> getFaceDataList() {
        return faceDataList;
    }

    public void setFaceDataList(List<FaceDataEntity> faceDataList) {
        this.faceDataList = faceDataList;
    }
}
