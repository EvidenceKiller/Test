package com.adl.service.entity;

public class ExerciseSumTimeRankEntity {

    //  账号Id
    private String accountId;
    //  账号名
    private String accountName;
    //  性别 1：男2 ：女
    private String sex;
    //  用户头像
    private String userAvatar;
    //  运动总时长
    private String sportTime;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getSportTime() {
        return sportTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }
}
