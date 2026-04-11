package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class ClassListRequest extends BasePageRequest {

    /**
     * 机构标识码
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 年级编码
     */
    @SerializedName("gradeCode")
    private String gradeCode;

    /**
     * 学年学期code
     */
    @SerializedName("acayearSem")
    private String acayearSem;

    /**
     * 年级编码
     */
    @SerializedName("keyword")
    private String keyword;

    /**
     * 学院id
     */
    @SerializedName("collegeId")
    private String collegeId;

    /**
     * 学系id
     */
    @SerializedName("facultyId")
    private String facultyId;

    /**
     * 专业id
     */
    @SerializedName("majorId")
    private String majorId;

    private ClassListRequest(Builder builder) {
        super(builder);
        this.orgId = builder.orgId;
        this.gradeCode = builder.gradeCode;
        this.acayearSem = builder.acayearSem;
        this.keyword = builder.keyword;
        this.collegeId = builder.collegeId;
        this.facultyId = builder.facultyId;
        this.majorId = builder.majorId;
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
        private String gradeCode;
        private String acayearSem;
        private String keyword;
        private String collegeId;
        private String facultyId;
        private String majorId;

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder gradeCode(String gradeCode) {
            this.gradeCode = gradeCode;
            return this;
        }

        public Builder acayearSem(String acayearSem) {
            this.acayearSem = acayearSem;
            return this;
        }

        public Builder keyword(String keyword) {
            this.keyword = keyword;
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

        public ClassListRequest build() {
            return new ClassListRequest(this);
        }
    }

    public String getOrgId() {
        return orgId;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public String getAcayearSem() {
        return acayearSem;
    }

    public String getKeyword() {
        return keyword;
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