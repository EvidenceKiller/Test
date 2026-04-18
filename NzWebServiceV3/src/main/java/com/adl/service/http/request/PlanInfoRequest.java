package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class PlanInfoRequest extends BaseRequest {
    /**
     * 计划ID
     */
    @SerializedName("planId")
    private String planId;

    private PlanInfoRequest(Builder builder) {
        super(builder);
        this.planId = builder.planId;
    }

    public static Builder builder(String planId) {
        return new Builder(planId);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        private String planId;

        Builder(String planId) {
            this.planId = planId;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public PlanInfoRequest build() {
            return new PlanInfoRequest(this);
        }


    }

}