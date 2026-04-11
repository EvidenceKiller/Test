package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class TeachCourseDataDatesRequest {
    /**
     * 班级Id
     */
    @SerializedName("sceneCodes")
    private List<String> sceneCodes;

    /**
     * 运动Id
     */
    @SerializedName("skuIds")
    private List<String> skuIds;

    /**
     * 运动项目编码
     */
    @SerializedName("classId")
    private String classId;


    private TeachCourseDataDatesRequest(Builder builder) {
        this.sceneCodes = builder.sceneCodes;
        this.skuIds = builder.skuIds;
        this.classId = builder.classId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> sceneCodes;
        private List<String> skuIds;
        private String classId;

        public Builder sceneCodes(List<String> sceneCodes) {
            this.sceneCodes = sceneCodes;
            return this;
        }

        public Builder skuIds(List<String> skuIds) {
            this.skuIds = skuIds;
            return this;
        }

        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }

        public TeachCourseDataDatesRequest build() {
            return new TeachCourseDataDatesRequest(this);
        }
    }

    public List<String> getSceneCodes() {
        return sceneCodes;
    }

    public List<String> getSkuIds() {
        return skuIds;
    }

    public String getClassId() {
        return classId;
    }
}
