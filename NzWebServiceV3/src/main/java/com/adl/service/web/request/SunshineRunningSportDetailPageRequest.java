package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

/**
 * Name : SportDetailListRequest
 * Author : zhouxiangnan
 * Date : 2026/4/7
 * Describe : 定义接口请求参数结构
 */
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String semesterId;
        private String orgId;
        private String date;

        public Builder semesterId(String v) {
            this.semesterId = v;
            return this;
        }

        public Builder orgId(String v) {
            this.orgId = v;
            return this;
        }

        public Builder date(String v) {
            this.date = v;
            return this;
        }

        public SunshineRunningSportDetailPageRequest build() {
            return new SunshineRunningSportDetailPageRequest(this);
        }
    }

    public String getSemesterId() {
        return semesterId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getDate() {
        return date;
    }
}