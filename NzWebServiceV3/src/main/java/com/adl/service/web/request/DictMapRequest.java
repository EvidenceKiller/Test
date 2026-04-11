package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class DictMapRequest {

    /**
     * 运动编码
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    private DictMapRequest(Builder builder) {
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

        public DictMapRequest build() {
            return new DictMapRequest(this);
        }
    }

    public String getSportSkuId() {
        return sportSkuId;
    }
}