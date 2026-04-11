package com.adl.service.entity;

import java.io.Serializable;

public class VictoryRankEntity implements Serializable {
    //"accountId": "1905228987873435691",
    //            "accountName": "张475",
    //            "faceUrl": "V2/busi/userFaceImgUrl/2025-04-10/1744276735799_b21e6ed7.jpg",
    //            "victoryNumber": 1,
    //            "victoryRate": 100.0

    private String accountId;
    private String accountName;
    private String faceUrl;
    private int victoryNumber;
    private double victoryRate;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

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

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public int getVictoryNumber() {
        return victoryNumber;
    }

    public void setVictoryNumber(int victoryNumber) {
        this.victoryNumber = victoryNumber;
    }

    public double getVictoryRate() {
        return victoryRate;
    }

    public void setVictoryRate(double victoryRate) {
        this.victoryRate = victoryRate;
    }
}
