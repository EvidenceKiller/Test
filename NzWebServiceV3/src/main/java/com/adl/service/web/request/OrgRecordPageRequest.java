package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class OrgRecordPageRequest extends BasePageRequest {

    /**
     * 组织测试计划标识码
     */
    @SerializedName("ptPlanId")
    private String ptPlanId;

    /**
     * 运动类型代码
     */
    @SerializedName("sportTypeCode")
    private String sportTypeCode;

    /**
     * 运动单项代码
     */
    @SerializedName("sportItemCode")
    private String sportItemCode;

    /**
     * 学期id
     */
    @SerializedName("semesterId")
    private String semesterId;

    /**
     * 场景Code
     */
    @SerializedName("sceneCode")
    private String sceneCode;

    /**
     * 应用Code
     */
    @SerializedName("appCode")
    private String appCode;

    /**
     * 用户ID
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 项目ID
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 年级id
     */
    @SerializedName("gradeId")
    private String gradeId;

    private OrgRecordPageRequest(Builder builder) {
        super(builder);
        this.ptPlanId = builder.ptPlanId;
        this.sportTypeCode = builder.sportTypeCode;
        this.sportItemCode = builder.sportItemCode;
        this.semesterId = builder.semesterId;
        this.sceneCode = builder.sceneCode;
        this.appCode = builder.appCode;
        this.accountId = builder.accountId;
        this.sportSkuId = builder.sportSkuId;
        this.gradeId = builder.gradeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String ptPlanId;
        private String sportTypeCode;
        private String sportItemCode;
        private String semesterId;
        private String sceneCode;
        private String appCode;
        private String accountId;
        private String sportSkuId;
        private String gradeId;

        public Builder ptPlanId(String ptPlanId) {
            this.ptPlanId = ptPlanId;
            return this;
        }

        public Builder sportTypeCode(String sportTypeCode) {
            this.sportTypeCode = sportTypeCode;
            return this;
        }

        public Builder sportItemCode(String sportItemCode) {
            this.sportItemCode = sportItemCode;
            return this;
        }

        public Builder semesterId(String semesterId) {
            this.semesterId = semesterId;
            return this;
        }

        public Builder sceneCode(String sceneCode) {
            this.sceneCode = sceneCode;
            return this;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }

        public Builder gradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public OrgRecordPageRequest build() {
            return new OrgRecordPageRequest(this);
        }
    }

    public String getPtPlanId() {
        return ptPlanId;
    }

    public String getSportTypeCode() {
        return sportTypeCode;
    }

    public String getSportItemCode() {
        return sportItemCode;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public String getGradeId() {
        return gradeId;
    }
}