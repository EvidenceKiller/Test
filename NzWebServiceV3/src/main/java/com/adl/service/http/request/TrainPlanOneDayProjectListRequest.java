package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class TrainPlanOneDayProjectListRequest extends BaseRequest {

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
        super(builder);
        this.trainPlanId = builder.trainPlanId;
        this.accountId = builder.accountId;
        this.trainPlanTime = builder.trainPlanTime;
    }

    public static Builder builder(String accountId, String trainPlanId) {
        return new Builder(accountId, trainPlanId);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String trainPlanId;
        private String accountId;
        private String trainPlanTime;

        Builder(String accountId, String trainPlanId) {
            this.accountId = accountId;
            this.trainPlanId = trainPlanId;
        }

        public Builder trainPlanTime(String trainPlanTime) {
            this.trainPlanTime = trainPlanTime;
            return this;
        }

        public TrainPlanOneDayProjectListRequest build() {
            return new TrainPlanOneDayProjectListRequest(this);
        }
    }
}