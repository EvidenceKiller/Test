package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class DownloadGroupTeamDetailPageRequest extends BasePageRequest {

    /**
     * 运动会id
     */
    @SerializedName("meetId")
    private String meetId;

    /**
     * 运动会项目id
     */
    @SerializedName("meetSportSkuId")
    private String meetSportSkuId;

    /**
     * 比赛方式 1集体 2个人
     */
    @SerializedName("competitionType")
    private Integer competitionType;

    /**
     * 比赛轮次
     */
    @SerializedName("competitionRound")
    private Integer competitionRound;

    /**
     * 查看版本时间
     */
    @SerializedName("checkTime")
    private String checkTime;

    /**
     * 分组单位
     */
    @SerializedName("teamUnitCode")
    private String teamUnitCode;

    private DownloadGroupTeamDetailPageRequest(Builder builder) {
        super(builder);
        this.meetId = builder.meetId;
        this.meetSportSkuId = builder.meetSportSkuId;
        this.competitionType = builder.competitionType;
        this.competitionRound = builder.competitionRound;
        this.checkTime = builder.checkTime;
        this.teamUnitCode = builder.teamUnitCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String meetId;
        private String meetSportSkuId;
        private Integer competitionType;
        private Integer competitionRound;
        private String checkTime;
        private String teamUnitCode;

        public Builder meetId(String meetId) {
            this.meetId = meetId;
            return this;
        }

        public Builder meetSportSkuId(String meetSportSkuId) {
            this.meetSportSkuId = meetSportSkuId;
            return this;
        }

        public Builder competitionType(Integer competitionType) {
            this.competitionType = competitionType;
            return this;
        }

        public Builder competitionRound(Integer competitionRound) {
            this.competitionRound = competitionRound;
            return this;
        }

        public Builder checkTime(String checkTime) {
            this.checkTime = checkTime;
            return this;
        }

        public Builder teamUnitCode(String teamUnitCode) {
            this.teamUnitCode = teamUnitCode;
            return this;
        }

        public DownloadGroupTeamDetailPageRequest build() {
            return new DownloadGroupTeamDetailPageRequest(this);
        }
    }
}