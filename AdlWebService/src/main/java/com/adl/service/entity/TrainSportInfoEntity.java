package com.adl.service.entity;

import java.io.Serializable;

/**
 * "trainPlanId": "fb59293d4c0d44eaa6b2f57ec481e5ff",
 * "sort": 1,
 * "sportCount": -1,
 * "sportModel": "1",
 * "sportTime": 60000,
 * "sportResult": null,
 * "checkStatus": false,
 * "sportSkuId": "4501010002",
 * "sportSkuName": "5人下蹲",
 * "trainPlanSportId": "58cc4e6a1da14ea49552f9aad0382f13"
 */
public class TrainSportInfoEntity implements Serializable {

    private String trainPlanId;
    private int sort;
    private int sportCount;
    private String sportModel;
    private long sportTime;
    private String sportResult;
    private boolean checkStatus;
    private String sportSkuId;
    private String sportSkuName;
    private String trainPlanSportId;

    public String getTrainPlanId() {
        return trainPlanId;
    }

    public void setTrainPlanId(String trainPlanId) {
        this.trainPlanId = trainPlanId;
    }

    public int getSportCount() {
        return sportCount;
    }

    public void setSportCount(int sportCount) {
        this.sportCount = sportCount;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSportModel() {
        return sportModel;
    }

    public void setSportModel(String sportModel) {
        this.sportModel = sportModel;
    }

    public long getSportTime() {
        return sportTime;
    }

    public void setSportTime(long sportTime) {
        this.sportTime = sportTime;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
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

    public String getTrainPlanSportId() {
        return trainPlanSportId;
    }

    public void setTrainPlanSportId(String trainPlanSportId) {
        this.trainPlanSportId = trainPlanSportId;
    }
}
