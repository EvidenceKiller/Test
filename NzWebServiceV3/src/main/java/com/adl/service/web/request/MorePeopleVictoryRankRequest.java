package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class MorePeopleVictoryRankRequest {

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
        this.skuId = builder.skuId;
        this.maxRank = builder.maxRank;
        this.top = builder.top;
    }

    public static Builder builder(String skuId) {
        return new Builder(skuId);
    }


    public static class Builder {
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

    public String getSkuId() {
        return skuId;
    }

    public Integer getMaxRank() {
        return maxRank;
    }

    public Integer getTop() {
        return top;
    }
}