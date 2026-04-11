package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class SportPhysicalTrainingRankRequest {

    /** 场景code 1001 锻炼 */
    @SerializedName("sportSceneCodes")
    private List<String> sportSceneCodes;

    /** 运动id */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /** 日期类型 1：今日 2：七日 3：三十日 */
    @SerializedName("dayType")
    private String dayType;

    /** 排行数 */
    @SerializedName("rankNumber")
    private Long rankNumber;

    /** 应用端 */
    @SerializedName("appCode")
    private String appCode;

    private SportPhysicalTrainingRankRequest(Builder builder) {
        this.sportSceneCodes = builder.sportSceneCodes;
        this.sportSkuId = builder.sportSkuId;
        this.dayType = builder.dayType;
        this.rankNumber = builder.rankNumber;
        this.appCode = builder.appCode;
    }

    public static Builder builder(List<String> sportSceneCodes, String sportSkuId, String dayType, String appCode) {
        return new Builder(sportSceneCodes, sportSkuId, dayType, appCode);
    }


    public static class Builder {
        private List<String> sportSceneCodes;
        private String sportSkuId;
        private String dayType;
        private Long rankNumber;
        private String appCode;
        Builder(List<String> sportSceneCodes, String sportSkuId, String dayType, String appCode) {
            this.sportSceneCodes = sportSceneCodes;
            this.sportSkuId = sportSkuId;
            this.dayType = dayType;
            this.appCode = appCode;
        }
        public Builder rankNumber(Long rankNumber) {
            this.rankNumber = rankNumber;
            return this;
        }

        public SportPhysicalTrainingRankRequest build() { return new SportPhysicalTrainingRankRequest(this); }
    }

    public List<String> getSportSceneCodes() { return sportSceneCodes; }
    public String getSportSkuId() { return sportSkuId; }
    public String getDayType() { return dayType; }
    public Long getRankNumber() { return rankNumber; }
    public String getAppCode() { return appCode; }
}