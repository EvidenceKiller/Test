package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class CompetitionRankDetailRequest {

    /**
     * ID
     */
    @SerializedName("id")
    private String id;

    /**
     * 账户ID
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 班级ID
     */
    @SerializedName("classId")
    private String classId;

    private CompetitionRankDetailRequest(Builder builder) {
        this.id = builder.id;
        this.accountId = builder.accountId;
        this.classId = builder.classId;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String id;
        private String accountId;
        private String classId;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }

        public CompetitionRankDetailRequest build() {
            return new CompetitionRankDetailRequest(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getClassId() {
        return classId;
    }
}