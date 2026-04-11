package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class CompetitionRankRequest {

    /**
     * 赛事id
     */
    @SerializedName("competitionId")
    private String competitionId;

    /**
     * 排行类型 1：总榜2：学校 3：年级 4：班级
     */
    @SerializedName("rankType")
    private String rankType;

    /**
     * 排行id （学校/年级/班级）id
     */
    @SerializedName("rankId")
    private String rankId;

    /**
     * 运动id
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 排名的数量
     */
    @SerializedName("rankNumber")
    private Long rankNumber;

    /**
     * 账号id
     */
    @SerializedName("accountId")
    private String accountId;

    private CompetitionRankRequest(Builder builder) {
        this.competitionId = builder.competitionId;
        this.rankType = builder.rankType;
        this.rankId = builder.rankId;
        this.sportSkuId = builder.sportSkuId;
        this.rankNumber = builder.rankNumber;
        this.accountId = builder.accountId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String competitionId;
        private String rankType;
        private String rankId;
        private String sportSkuId;
        private Long rankNumber;
        private String accountId;

        public Builder competitionId(String competitionId) {
            this.competitionId = competitionId;
            return this;
        }

        public Builder rankType(String rankType) {
            this.rankType = rankType;
            return this;
        }

        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }

        public Builder rankId(String rankId) {
            this.rankId = rankId;
            return this;
        }

        public Builder rankNumber(Long rankNumber) {
            this.rankNumber = rankNumber;
            return this;
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public CompetitionRankRequest build() {
            return new CompetitionRankRequest(this);
        }
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public String getRankType() {
        return rankType;
    }

    public String getRankId() {
        return rankId;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public Long getRankNumber() {
        return rankNumber;
    }

    public String getAccountId() {
        return accountId;
    }
}