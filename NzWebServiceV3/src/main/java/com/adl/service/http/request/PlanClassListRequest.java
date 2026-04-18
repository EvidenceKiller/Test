package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class PlanClassListRequest extends BasePageRequest {

    /**
     * 计划ID
     */
    @SerializedName("planId")
    private String planId;

    /**
     * 机构ID
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 测试类型
     */
    @SerializedName("standardType")
    private String standardType;

    /**
     * 学年
     */
    @SerializedName("acayearCode")
    private String acayearCode;

    /**
     * 状态 1未开始 2进行中 3已结束
     */
    @SerializedName("status")
    private String status;

    /**
     * 是否显示统计
     */
    @SerializedName("showStatistic")
    private Boolean showStatistic;

    private PlanClassListRequest(Builder builder) {
        super(builder);
        this.planId = builder.planId;
        this.orgId = builder.orgId;
        this.standardType = builder.standardType;
        this.acayearCode = builder.acayearCode;
        this.status = builder.status;
        this.showStatistic = builder.showStatistic;
    }

    public static Builder builder(String planId) {
        return new Builder(planId);
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        private String planId;
        private String orgId;
        private String standardType;
        private String acayearCode;
        private String status;
        private Boolean showStatistic;

        Builder(String planId) {
            this.planId = planId;
        }

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder standardType(String standardType) {
            this.standardType = standardType;
            return this;
        }

        public Builder acayearCode(String acayearCode) {
            this.acayearCode = acayearCode;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder showStatistic(Boolean showStatistic) {
            this.showStatistic = showStatistic;
            return this;
        }

        public PlanClassListRequest build() {
            return new PlanClassListRequest(this);
        }
    }
}