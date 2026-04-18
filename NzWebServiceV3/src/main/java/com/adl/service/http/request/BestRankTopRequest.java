package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class BestRankTopRequest extends BaseRequest {

    /**
     * 产品应用标识码
     */
    @SerializedName("appCode")
    private String appCode;

    /**
     * 运动sku标识码
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

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
     * 账户性别
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 前几的排行榜
     */
    @SerializedName("top")
    private Integer top;

    /**
     * 1-今日 2-本周 3-本月 4-本季度 5-本年度
     */
    @SerializedName("timeType")
    private Integer timeType;

    /**
     * 运动场景代码
     */
    @SerializedName("sportSceneCode")
    private String sportSceneCode;

    /**
     * 运动场景类型
     */
    @SerializedName("sportSceneType")
    private String sportSceneType;

    private BestRankTopRequest(Builder builder) {
        super(builder);
        this.appCode = builder.appCode;
        this.sportSkuId = builder.sportSkuId;
        this.classId = builder.classId;
        this.gradeId = builder.gradeId;
        this.orgId = builder.orgId;
        this.collegeId = builder.collegeId;
        this.facultyId = builder.facultyId;
        this.majorId = builder.majorId;
        this.sex = builder.sex;
        this.top = builder.top;
        this.timeType = builder.timeType;
        this.sportSceneCode = builder.sportSceneCode;
        this.sportSceneType = builder.sportSceneType;
    }

    public static Builder builder(String sportSkuId, Integer timeType) {
        return new Builder(sportSkuId, timeType);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String appCode;
        private String sportSkuId;
        private String classId;
        private String gradeId;
        private String orgId;
        private String collegeId;
        private String facultyId;
        private String majorId;
        private Integer sex;
        private Integer top;
        private Integer timeType;
        private String sportSceneCode;
        private String sportSceneType;

        Builder(String sportSkuId, Integer timeType) {
            this.sportSkuId = sportSkuId;
            this.timeType = timeType;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
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

        public Builder sex(Integer sex) {
            this.sex = sex;
            return this;
        }

        public Builder top(Integer top) {
            this.top = top;
            return this;
        }

        public Builder sportSceneCode(String sportSceneCode) {
            this.sportSceneCode = sportSceneCode;
            return this;
        }

        public Builder sportSceneType(String sportSceneType) {
            this.sportSceneType = sportSceneType;
            return this;
        }

        public BestRankTopRequest build() {
            return new BestRankTopRequest(this);
        }
    }
}