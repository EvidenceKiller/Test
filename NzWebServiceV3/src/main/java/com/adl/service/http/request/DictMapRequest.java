package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class DictMapRequest extends BaseRequest {

    /**
     * 运动编码
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    private DictMapRequest(Builder builder) {
        super(builder);
        this.sportSkuId = builder.sportSkuId;
    }

    public static Builder builder(String sportSkuId) {
        return new Builder(sportSkuId);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String sportSkuId;

        Builder(String sportSkuId) {
            this.sportSkuId = sportSkuId;
        }

        public DictMapRequest build() {
            return new DictMapRequest(this);
        }
    }
}