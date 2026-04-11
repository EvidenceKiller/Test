package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class CheckStudentInPlanRequest {

    /** 计划 id */
    @SerializedName("planId")
    private String planId;

    /** 账号 id */
    @SerializedName("accountId")
    private String accountId;

    private CheckStudentInPlanRequest(Builder builder) {
        this.planId = builder.planId;
        this.accountId = builder.accountId;
    }

    public static Builder builder(String planId) {
        return new Builder(planId);
    }


    public static class Builder {
        private String planId;
        private String accountId;
        Builder(String planId) {
            this.planId = planId;
        }
        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public CheckStudentInPlanRequest build() { return new CheckStudentInPlanRequest(this); }
    }

    public String getPlanId() { return planId; }
    public String getAccountId() { return accountId; }
}