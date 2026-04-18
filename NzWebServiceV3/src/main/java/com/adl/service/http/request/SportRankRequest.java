package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class SportRankRequest extends BaseRequest {

    /** 场景code 1001 锻炼 */
    @SerializedName("sportSceneCodes")
    private List<String> sportSceneCodes;

    /** 运动场景类型 */
    @SerializedName("sportSceneType")
    private String sportSceneType;

    /** 运动id */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /** 日期类型 1：今日 2：七日 3：三十日 */
    @SerializedName("dayType")
    private String dayType;

    /** 机构id */
    @SerializedName("orgId")
    private String orgId;

    /** 租户机构id */
    @SerializedName("tenantOrgId")
    private String tenantOrgId;

    /** 运动时间 */
    @SerializedName("sportDateList")
    private List<String> sportDateList;

    /** 排行数 */
    @SerializedName("rankNumber")
    private Long rankNumber;

    /** 应用端 */
    @SerializedName("appCode")
    private String appCode;

    private SportRankRequest(Builder builder) {
        super(builder);
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

    public static Builder builder(String appCode, String sportSceneType) {
        return new Builder(appCode, sportSceneType);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private List<String> sportSceneCodes;
        private String sportSceneType;
        private String sportSkuId;
        private String dayType;
        private String orgId;
        private String tenantOrgId;
        private List<String> sportDateList;
        private Long rankNumber;
        private String appCode;

        Builder(String appCode, String sportSceneType) {
            this.appCode = appCode;
            this.sportSceneType = sportSceneType;
        }

        public Builder sportSceneCodes(List<String> sportSceneCodes) {
            this.sportSceneCodes = sportSceneCodes;
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

        public SportRankRequest build() { return new SportRankRequest(this); }
    }
}