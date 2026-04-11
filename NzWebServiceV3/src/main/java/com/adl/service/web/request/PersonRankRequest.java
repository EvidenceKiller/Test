package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class PersonRankRequest {

    /**
     * 人员id
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 运动sku标识码
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 运动sku子类
     */
    @SerializedName("sportSkuSubType")
    private String sportSkuSubType;

    /**
     * 1-今日 2-本周 3-本月 4-本季度 5-本年度
     */
    @SerializedName("timeType")
    private Integer timeType;

    /**
     * 班级标识码
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 学籍标识码
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 机构标识码
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 学院标识码
     */
    @SerializedName("collegeId")
    private String collegeId;

    /**
     * 学系标识码
     */
    @SerializedName("facultyId")
    private String facultyId;

    /**
     * 专业标识码
     */
    @SerializedName("majorId")
    private String majorId;

    private PersonRankRequest(Builder builder) {
        this.accountId = builder.accountId;
        this.sportSkuId = builder.sportSkuId;
        this.sportSkuSubType = builder.sportSkuSubType;
        this.timeType = builder.timeType;
        this.classId = builder.classId;
        this.gradeId = builder.gradeId;
        this.orgId = builder.orgId;
        this.collegeId = builder.collegeId;
        this.facultyId = builder.facultyId;
        this.majorId = builder.majorId;
    }

    public static Builder builder(String accountId, String sportSkuId) {
        return new Builder(accountId, sportSkuId);
    }


    public static class Builder {
        private String accountId;
        private String sportSkuId;
        private String sportSkuSubType;
        private Integer timeType;
        private String classId;
        private String gradeId;
        private String orgId;
        private String collegeId;
        private String facultyId;
        private String majorId;

        Builder(String accountId, String sportSkuId) {
            this.accountId = accountId;
            this.sportSkuId = sportSkuId;
        }

        public Builder sportSkuSubType(String sportSkuSubType) {
            this.sportSkuSubType = sportSkuSubType;
            return this;
        }

        public Builder timeType(Integer timeType) {
            this.timeType = timeType;
            return this;
        }

        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }

        public Builder gradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder collegeId(String collegeId) {
            this.collegeId = collegeId;
            return this;
        }

        public Builder facultyId(String facultyId) {
            this.facultyId = facultyId;
            return this;
        }

        public Builder majorId(String majorId) {
            this.majorId = majorId;
            return this;
        }

        public PersonRankRequest build() {
            return new PersonRankRequest(this);
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public String getSportSkuSubType() {
        return sportSkuSubType;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public String getClassId() {
        return classId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public String getMajorId() {
        return majorId;
    }
}