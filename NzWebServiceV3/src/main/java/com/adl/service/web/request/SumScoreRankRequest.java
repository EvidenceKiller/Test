package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class SumScoreRankRequest {

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

    /**
     * 前几的排行榜
     */
    @SerializedName("topLimit")
    private Integer topLimit;

    private SumScoreRankRequest(Builder builder) {
        this.sportSkuId = builder.sportSkuId;
        this.sportSkuSubType = builder.sportSkuSubType;
        this.timeType = builder.timeType;
        this.classId = builder.classId;
        this.gradeId = builder.gradeId;
        this.orgId = builder.orgId;
        this.collegeId = builder.collegeId;
        this.facultyId = builder.facultyId;
        this.majorId = builder.majorId;
        this.topLimit = builder.topLimit;
    }

    public static Builder builder(String sportSkuId, Integer timeType) {
        return new Builder(sportSkuId, timeType);
    }


    public static class Builder {
        private String sportSkuId;
        private String sportSkuSubType;
        private Integer timeType;
        private String classId;
        private String gradeId;
        private String orgId;
        private String collegeId;
        private String facultyId;
        private String majorId;
        private Integer topLimit;

        Builder(String sportSkuId, int timeType) {
            this.sportSkuId = sportSkuId;
            this.timeType = timeType;
        }

        public Builder sportSkuSubType(String sportSkuSubType) {
            this.sportSkuSubType = sportSkuSubType;
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

        public Builder topLimit(Integer topLimit) {
            this.topLimit = topLimit;
            return this;
        }

        public SumScoreRankRequest build() {
            return new SumScoreRankRequest(this);
        }
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

    public Integer getTopLimit() {
        return topLimit;
    }
}