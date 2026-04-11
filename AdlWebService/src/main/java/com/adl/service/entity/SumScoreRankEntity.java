package com.adl.service.entity;

import java.io.Serializable;

public class SumScoreRankEntity implements Serializable {
    /**
     * "accountId": "string",
     *         "accountName": "string",
     *         "faceImgUrl": "string",
     *         "score": "string"
     */
    private String accountId;
    private String accountName;
    private String faceImgUrl;
    private String score;
    private String sex;

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

    public String getFaceImgUrl() {
        return faceImgUrl;
    }

    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
