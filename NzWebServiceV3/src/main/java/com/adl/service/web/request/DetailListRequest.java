package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class DetailListRequest {

    /** 班级Id */
    @SerializedName("classId")
    private String classId;

    /** 运动Id */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /** 运动单项代码 */
    @SerializedName("sportItemCode")
    private String sportItemCode;

    /** 日期 */
    @SerializedName("date")
    private String date;

    /** 次数 */
    @SerializedName("times")
    private Integer times;

    private DetailListRequest(Builder builder) {
        this.classId = builder.classId;
        this.sportSkuId = builder.sportSkuId;
        this.sportItemCode = builder.sportItemCode;
        this.date = builder.date;
        this.times = builder.times;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String classId;
        private String sportSkuId;
        private String sportItemCode;
        private String date;
        private Integer times;
        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }
        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }
        public Builder sportItemCode(String sportItemCode) {
            this.sportItemCode = sportItemCode;
            return this;
        }
        public Builder date(String date) {
            this.date = date;
            return this;
        }
        public Builder times(Integer times) {
            this.times = times;
            return this;
        }

        public DetailListRequest build() { return new DetailListRequest(this); }
    }

    public String getClassId() { return classId; }
    public String getSportSkuId() { return sportSkuId; }
    public String getSportItemCode() { return sportItemCode; }
    public String getDate() { return date; }
    public Integer getTimes() { return times; }
}