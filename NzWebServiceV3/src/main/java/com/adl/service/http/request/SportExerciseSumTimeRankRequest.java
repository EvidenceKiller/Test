package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class SportExerciseSumTimeRankRequest extends BaseRequest {

    /** 场景code 1001 锻炼 */
    @SerializedName("sportSceneCodes")
    private List<String> sportSceneCodes;

    /** 日期类型 1：今日 2：七日 3：三十日 */
    @SerializedName("dayType")
    private String dayType;

    /** 排行数 */
    @SerializedName("rankNumber")
    private Long rankNumber;

    /** 应用端 */
    @SerializedName("appCode")
    private String appCode;

    private SportExerciseSumTimeRankRequest(Builder builder) {
        super(builder);
        this.sportSceneCodes = builder.sportSceneCodes;
        this.dayType = builder.dayType;
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
        private Long rankNumber;
        private String appCode;
        Builder(List<String> sportSceneCodes, String dayType, String appCode) {
            this.sportSceneCodes = sportSceneCodes;
            this.dayType = dayType;
            this.appCode = appCode;
        }
        public Builder rankNumber(Long rankNumber) {
            this.rankNumber = rankNumber;
            return this;
        }

        public SportExerciseSumTimeRankRequest build() { return new SportExerciseSumTimeRankRequest(this); }
    }
}