package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class SportSportRankKingRequest extends BaseRequest {

    /** 场景code 1001 锻炼 */
    @SerializedName("sportSceneCodes")
    private List<String> sportSceneCodes;

    /** 日期类型 1：今日 2：七日 3：三十日 */
    @SerializedName("dayType")
    private String dayType;

    /** 机构id */
    @SerializedName("orgId")
    private String orgId;

    /** 排行数 */
    @SerializedName("rankNumber")
    private Long rankNumber;

    /** 应用端 */
    @SerializedName("appCode")
    private String appCode;

    private SportSportRankKingRequest(Builder builder) {
        super(builder);
        this.sportSceneCodes = builder.sportSceneCodes;
        this.dayType = builder.dayType;
        this.orgId = builder.orgId;
        this.rankNumber = builder.rankNumber;
        this.appCode = builder.appCode;
    }

    public static Builder builder(List<String> sportSceneCodes, String dayType, String appCode) {
        return new Builder(sportSceneCodes, dayType, appCode);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private List<String> sportSceneCodes;
        private String dayType;
        private String orgId;
        private Long rankNumber;
        private String appCode;
        Builder(List<String> sportSceneCodes, String dayType, String appCode) {
            this.sportSceneCodes = sportSceneCodes;
            this.dayType = dayType;
            this.appCode = appCode;
        }
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }
        public Builder rankNumber(Long rankNumber) {
            this.rankNumber = rankNumber;
            return this;
        }

        public SportSportRankKingRequest build() { return new SportSportRankKingRequest(this); }
    }
}