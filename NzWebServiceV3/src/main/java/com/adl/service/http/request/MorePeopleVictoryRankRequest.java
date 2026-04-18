package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class MorePeopleVictoryRankRequest extends BaseRequest {

    /**
     * SKU ID
     */
    @SerializedName("skuId")
    private String skuId;

    /**
     * 上榜的最大排名
     */
    @SerializedName("maxRank")
    private Integer maxRank;

    /**
     * 前N名
     */
    @SerializedName("top")
    private Integer top;

    private MorePeopleVictoryRankRequest(Builder builder) {
        super(builder);
        this.skuId = builder.skuId;
        this.maxRank = builder.maxRank;
        this.top = builder.top;
    }

    public static Builder builder(String skuId) {
        return new Builder(skuId);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String skuId;
        private Integer maxRank;
        private Integer top;

        Builder(String skuId) {
            this.skuId = skuId;
        }

        public Builder maxRank(Integer maxRank) {
            this.maxRank = maxRank;
            return this;
        }

        public Builder top(Integer top) {
            this.top = top;
            return this;
        }

        public MorePeopleVictoryRankRequest build() {
            return new MorePeopleVictoryRankRequest(this);
        }
    }
}