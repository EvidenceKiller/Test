package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class OverviewRequest extends BaseRequest {

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
     * 学级标识码
     */
    @SerializedName("gradeId")
    private String gradeId;

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

    private OverviewRequest(Builder builder) {
        super(builder);
        this.timeType = builder.timeType;
        this.classId = builder.classId;
        this.gradeId = builder.gradeId;
        this.collegeId = builder.collegeId;
        this.facultyId = builder.facultyId;
        this.majorId = builder.majorId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private Integer timeType;
        private String classId;
        private String gradeId;
        private String collegeId;
        private String facultyId;
        private String majorId;

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

        public OverviewRequest build() {
            return new OverviewRequest(this);
        }
    }
}