package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class TrainPlanOneDayProjectListRequest {

    /**
     * 训练计划 id
     */
    @SerializedName("trainPlanId")
    private String trainPlanId;

    /**
     * 用户 id
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 训练计划日期 (传就查询具体某一天，不传查询所有)
     */
    @SerializedName("trainPlanTime")
    private String trainPlanTime;

    private TrainPlanOneDayProjectListRequest(Builder builder) {
        this.trainPlanId = builder.trainPlanId;
        this.accountId = builder.accountId;
        this.trainPlanTime = builder.trainPlanTime;
    }

    public static Builder builder(String trainPlanId, String accountId) {
        return new Builder(trainPlanId, accountId);
    }


    public static class Builder {
        private String trainPlanId;
        private String accountId;
        private String trainPlanTime;

        Builder(String trainPlanId, String accountId) {
            this.trainPlanId = trainPlanId;
            this.accountId = accountId;
        }

        public Builder trainPlanTime(String trainPlanTime) {
            this.trainPlanTime = trainPlanTime;
            return this;
        }

        public TrainPlanOneDayProjectListRequest build() {
            return new TrainPlanOneDayProjectListRequest(this);
        }
    }

    public String getTrainPlanId() {
        return trainPlanId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTrainPlanTime() {
        return trainPlanTime;
    }
}