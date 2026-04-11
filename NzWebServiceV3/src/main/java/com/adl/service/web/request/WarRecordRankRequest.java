package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class WarRecordRankRequest {

    /**
     * 场景code数组
     */
    @SerializedName("sportSceneCodes")
    private List<String> sportSceneCodes;

    /**
     * 运动场景类型
     */
    @SerializedName("sportSceneType")
    private String sportSceneType;

    /**
     * 运动商品ID
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 日期类型 1:今日 2:七日 3:三十日
     */
    @SerializedName("dayType")
    private String dayType;

    /**
     * 机构ID
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 租户机构ID
     */
    @SerializedName("tenantOrgId")
    private String tenantOrgId;

    /**
     * 运动时间列表
     */
    @SerializedName("sportDateList")
    private List<String> sportDateList;

    /**
     * 排行数
     */
    @SerializedName("rankNumber")
    private Long rankNumber;

    /**
     * 应用端编码
     */
    @SerializedName("appCode")
    private String appCode;

    private WarRecordRankRequest(Builder builder) {
        this.sportSceneCodes = builder.sportSceneCodes;
        this.sportSceneType = builder.sportSceneType;
        this.sportSkuId = builder.sportSkuId;
        this.dayType = builder.dayType;
        this.orgId = builder.orgId;
        this.tenantOrgId = builder.tenantOrgId;
        this.sportDateList = builder.sportDateList;
        this.rankNumber = builder.rankNumber;
        this.appCode = builder.appCode;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private List<String> sportSceneCodes;
        private String sportSceneType;
        private String sportSkuId;
        private String dayType;
        private String orgId;
        private String tenantOrgId;
        private List<String> sportDateList;
        private Long rankNumber;
        private String appCode;

        public Builder sportSceneCodes(List<String> sportSceneCodes) {
            this.sportSceneCodes = sportSceneCodes;
            return this;
        }

        public Builder sportSceneType(String sportSceneType) {
            this.sportSceneType = sportSceneType;
            return this;
        }

        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }

        public Builder dayType(String dayType) {
            this.dayType = dayType;
            return this;
        }

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder tenantOrgId(String tenantOrgId) {
            this.tenantOrgId = tenantOrgId;
            return this;
        }

        public Builder sportDateList(List<String> sportDateList) {
            this.sportDateList = sportDateList;
            return this;
        }

        public Builder rankNumber(Long rankNumber) {
            this.rankNumber = rankNumber;
            return this;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public WarRecordRankRequest build() { return new WarRecordRankRequest(this); }
    }

    public String getSportSkuId() { return sportSkuId; }
}