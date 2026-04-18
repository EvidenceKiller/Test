package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class ClassInfoRequest extends BasePageRequest {
    /**
     * 班级id
     */
    @SerializedName("classId")
    private String classId;

    private ClassInfoRequest(Builder builder) {
        super(builder);
        this.classId = builder.classId;
    }

    public static Builder builder(String classId) {
        return new Builder(classId);
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String classId;

        Builder(String classId) {
            this.classId = classId;
        }

        public ClassInfoRequest build() {
            return new ClassInfoRequest(this);
        }
    }
}