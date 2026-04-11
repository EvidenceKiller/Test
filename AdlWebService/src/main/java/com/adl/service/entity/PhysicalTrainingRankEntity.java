package com.adl.service.entity;

import java.io.Serializable;

public class PhysicalTrainingRankEntity implements Serializable {

    //  账号Id
    private String accountId;
    //  账号名
    private String accountName;
    //  性别 1：男2 ：女
    private String sex;
    //  用户头像
    private String userAvatar;
    //  运动得分
    private String sportScore;
    //  运动成绩
    private String sportResult;
    //  成绩原始值
    private String originalSportResult;

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

    public String getSportScore() {
        return sportScore;
    }

    public void setSportScore(String sportScore) {
        this.sportScore = sportScore;
    }

    public String getOriginalSportResult() {
        return originalSportResult;
    }

    public void setOriginalSportResult(String originalSportResult) {
        this.originalSportResult = originalSportResult;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }
}
