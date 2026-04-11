package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class SportSkuDetailRequest {

    /** 运动编码 */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    private SportSkuDetailRequest(Builder builder) {
        this.sportSkuId = builder.sportSkuId;
    }

    public static Builder builder(String sportSkuId) {
        return new Builder(sportSkuId);
    }


    public static class Builder {
        private String sportSkuId;
        Builder(String sportSkuId) {
            this.sportSkuId = sportSkuId;
        }

        public SportSkuDetailRequest build() { return new SportSkuDetailRequest(this); }
    }

    public String getSportSkuId() { return sportSkuId; }
}