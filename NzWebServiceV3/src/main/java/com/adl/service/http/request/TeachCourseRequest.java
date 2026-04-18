package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class TeachCourseRequest extends BaseRequest {
    /**
     * 班级Id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 运动Id
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 运动项目编码
     */
    @SerializedName("sportItemCode")
    private String sportItemCode;

    /**
     * 日期
     */
    @SerializedName("date")
    private String date;

    /**
     * 次数
     */
    @SerializedName("times")
    private Integer times;

    private TeachCourseRequest(Builder builder) {
        super(builder);
        this.classId = builder.classId;
        this.sportSkuId = builder.sportSkuId;
        this.sportItemCode = builder.sportItemCode;
        this.date = builder.date;
        this.times = builder.times;
    }

    public static Builder builder(String classId, String date) {
        return new Builder(classId, date);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String classId;
        private String sportSkuId;
        private String sportItemCode;
        private String date;
        private Integer times;

        Builder(String classId, String date) {
            this.classId = classId;
            this.date = date;
        }

        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }

        public Builder sportItemCode(String sportItemCode) {
            this.sportItemCode = sportItemCode;
            return this;
        }

        public Builder times(Integer times) {
            this.times = times;
            return this;
        }

        public TeachCourseRequest build() {
            return new TeachCourseRequest(this);
        }
    }
}
