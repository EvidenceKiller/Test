package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class TeacherListRequest extends BasePageRequest {

    /**
     * 机构id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 账户类型
     */
    @SerializedName("accountType")
    private Integer accountType;

    /**
     * 最后更新时间
     */
    @SerializedName("lastUpdateTime")
    private String lastUpdateTime;

    /**
     * 学年代码
     */
    @SerializedName("acayearSem")
    private String acayearSem;

    /**
     * 学期代码
     */
    @SerializedName("acayearSemCode")
    private String acayearSemCode;

    /**
     * 1 百度 2 商汤
     */
    @SerializedName("faceType")
    private Integer faceType;

    @SerializedName("delFlag")
    private Boolean delFlag;

    private TeacherListRequest(Builder builder) {
        super(builder);
        this.orgId = builder.orgId;
        this.accountType = builder.accountType;
        this.lastUpdateTime = builder.lastUpdateTime;
        this.acayearSem = builder.acayearSem;
        this.acayearSemCode = builder.acayearSemCode;
        this.faceType = builder.faceType;
        this.delFlag = builder.delFlag;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        private String orgId;
        private Integer accountType;
        private String lastUpdateTime;
        private String acayearSem;
        private String acayearSemCode;
        private Integer faceType;
        private Boolean delFlag;

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder accountType(Integer accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder lastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
            return this;
        }

        public Builder acayearSem(String acayearSem) {
            this.acayearSem = acayearSem;
            return this;
        }

        public Builder acayearSemCode(String acayearSemCode) {
            this.acayearSemCode = acayearSemCode;
            return this;
        }

        public Builder faceType(Integer faceType) {
            this.faceType = faceType;
            return this;
        }

        public Builder delFlag(Boolean delFlag) {
            this.delFlag = delFlag;
            return this;
        }

        public TeacherListRequest build() {
            return new TeacherListRequest(this);
        }
    }

    public String getOrgId() {
        return orgId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getAcayearSem() {
        return acayearSem;
    }

    public String getAcayearSemCode() {
        return acayearSemCode;
    }

    public Integer getFaceType() {
        return faceType;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }
}