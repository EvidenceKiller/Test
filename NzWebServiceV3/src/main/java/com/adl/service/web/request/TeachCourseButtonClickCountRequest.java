package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class TeachCourseButtonClickCountRequest {
    /**
     * 班级Id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 日期
     */
    @SerializedName("date")
    private String date;


    private TeachCourseButtonClickCountRequest(Builder builder) {
        this.classId = builder.classId;
        this.date = builder.date;
    }

    public static Builder builder(String classId, String data) {
        return new Builder(classId, data);
    }

    public static class Builder {
        private String classId;
        private String date;

        private Builder(String classId, String data) {
            this.classId = classId;
            this.date = data;
        }

        public TeachCourseButtonClickCountRequest build() {
            return new TeachCourseButtonClickCountRequest(this);
        }
    }

    public String getClassId() {
        return classId;
    }

    public String getDate() {
        return date;
    }

}
