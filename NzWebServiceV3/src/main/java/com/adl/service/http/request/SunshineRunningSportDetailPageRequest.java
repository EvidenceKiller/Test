package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Name : SportDetailListRequest
 * Author : zhouxiangnan
 * Date : 2026/4/7
 * Describe : 定义接口请求参数结构
 */
@Getter
public final class SunshineRunningSportDetailPageRequest extends BasePageRequest {

    /**
     * 学期Id
     */
    @SerializedName("semesterId")
    private String semesterId;

    /**
     * 机构Id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 日期
     */
    @SerializedName("date")
    private String date;

    private SunshineRunningSportDetailPageRequest(Builder b) {
        super(b);
        this.semesterId = b.semesterId;
        this.orgId = b.orgId;
        this.date = b.date;
    }

    public static Builder builder(String date) {
        return new Builder(date);
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String semesterId;
        private String orgId;
        private String date;

        public Builder(String date) {
            this.date = date;
        }

        public Builder semesterId(String v) {
            this.semesterId = v;
            return this;
        }

        public Builder orgId(String v) {
            this.orgId = v;
            return this;
        }

        public SunshineRunningSportDetailPageRequest build() {
            return new SunshineRunningSportDetailPageRequest(this);
        }
    }
}