package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class LogAddBatchRequest {

    /**
     * 应用编码
     */
    @SerializedName("appCode")
    private String appCode;

    /**
     * 日志内容
     */
    @SerializedName("content")
    private String content;

    /**
     * 日志过期时间，单位秒，范围在86400到86400*60即1-7天之间,如果不传默认存储1天
     */
    @SerializedName("expireTime")
    private String expireTime;

    /**
     * 日志级别 常规日志级别，通常为 info(默认) warn error fatal debug trace
     */
    @SerializedName("level")
    private String level;

    /**
     * 产品编码
     */
    @SerializedName("productCode")
    private String productCode;

    /**
     * 日志标签，用来快速查询日志信息,由各业务端自行定义
     */
    @SerializedName("tags")
    private List<String> tags;

    private LogAddBatchRequest(Builder builder) {
        this.appCode = builder.appCode;
        this.content = builder.content;
        this.expireTime = builder.expireTime;
        this.level = builder.level;
        this.productCode = builder.productCode;
        this.tags = builder.tags;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String appCode;
        private String content;
        private String expireTime;
        private String level;
        private String productCode;
        private List<String> tags;

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder expireTime(String expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Builder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public LogAddBatchRequest build() {
            return new LogAddBatchRequest(this);
        }
    }

    public String getAppCode() {
        return appCode;
    }

    public String getContent() {
        return content;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public String getLevel() {
        return level;
    }

    public String getProductCode() {
        return productCode;
    }

    public List<String> getTags() {
        return tags;
    }
}