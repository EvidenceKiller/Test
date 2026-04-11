package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

/**
 * Name : SportDetailListRequest
 * Author : zhouxiangnan
 * Date : 2026/4/7
 * Describe : 定义接口请求参数结构
 */
public final class SunshineRunningCompetitionRecordPageRequest extends BasePageRequest {

    /**
     * 机构id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 学级
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 班级id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 组别
     */
    @SerializedName("multiPersonIndex")
    private Integer multiPersonIndex;

    /**
     * 开始日期
     */
    @SerializedName("startDate")
    private String startDate;

    /**
     * 结束日期
     */
    @SerializedName("endDate")
    private String endDate;

    /**
     * 项目
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 应用端
     */
    @SerializedName("appCode")
    private String appCode;

    /**
     * 关键字
     */
    @SerializedName("keyword")
    private String keyword;

    private SunshineRunningCompetitionRecordPageRequest(Builder builder) {
        super(builder);
        this.orgId = builder.orgId;
        this.gradeId = builder.gradeId;
        this.classId = builder.classId;
        this.multiPersonIndex = builder.multiPersonIndex;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.sportSkuId = builder.sportSkuId;
        this.appCode = builder.appCode;
        this.keyword = builder.keyword;
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
        private String gradeId;
        private String classId;
        private Integer multiPersonIndex;
        private String startDate;
        private String endDate;
        private String sportSkuId;
        private String appCode;
        private String keyword;

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder gradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }

        public Builder multiPersonIndex(Integer multiPersonIndex) {
            this.multiPersonIndex = multiPersonIndex;
            return this;
        }

        public Builder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public SunshineRunningCompetitionRecordPageRequest build() {
            return new SunshineRunningCompetitionRecordPageRequest(this);
        }
    }

    public String getOrgId() {
        return orgId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public Integer getMultiPersonIndex() {
        return multiPersonIndex;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public String getAppCode() {
        return appCode;
    }

    public String getKeyword() {
        return keyword;
    }
}