package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class PlanPageRequest extends BasePageRequest {

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

    /**
     * 计划ID
     */
    @SerializedName("planId")
    private String planId;

    private PlanPageRequest(Builder builder) {
        super(builder);
        this.orgId = builder.orgId;
        this.standardType = builder.standardType;
        this.acayearCode = builder.acayearCode;
        this.status = builder.status;
        this.showStatistic = builder.showStatistic;
        this.planId = builder.planId;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        private String orgId;
        private String standardType;
        private String acayearCode;
        private String status;
        private Boolean showStatistic;
        private String planId;

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

        public Builder planId(String planId) {
            this.planId = planId;
            return this;
        }

        public PlanPageRequest build() {
            return new PlanPageRequest(this);
        }
    }
}