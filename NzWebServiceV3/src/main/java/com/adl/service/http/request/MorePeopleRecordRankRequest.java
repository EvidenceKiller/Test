package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class MorePeopleRecordRankRequest extends BaseRequest {

    /**
     * 运动id
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 机构id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 对战组数
     */
    @SerializedName("rankNumber")
    private Long rankNumber;

    private MorePeopleRecordRankRequest(Builder builder) {
        super(builder);
        this.sportSkuId = builder.sportSkuId;
        this.orgId = builder.orgId;
        this.rankNumber = builder.rankNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String sportSkuId;
        private String orgId;
        private Long rankNumber;

        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder rankNumber(Long rankNumber) {
            this.rankNumber = rankNumber;
            return this;
        }

        public MorePeopleRecordRankRequest build() {
            return new MorePeopleRecordRankRequest(this);
        }
    }
}