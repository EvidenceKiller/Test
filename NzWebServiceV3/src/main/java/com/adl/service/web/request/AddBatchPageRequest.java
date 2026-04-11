package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class AddBatchPageRequest extends BasePageRequest {

    /**
     * 产品编码
     */
    @SerializedName("productCode")
    private String productCode;

    /**
     * 应用编码
     */
    @SerializedName("appCode")
    private String appCode;

    /**
     * 日志级别 常规日志级别，通常为 info(默认) warn error fatal debug trace
     */
    @SerializedName("level")
    private String level;

    /**
     * 日志标签，用来快速查询日志信息，由各业务端自行定义
     */
    @SerializedName("tags")
    private List<String> tags;

    /**
     * 日志内容
     */
    @SerializedName("content")
    private String content;

    /**
     * 日志过期时间，单位秒，范围在 86400 到 86400*60 即 1-7 天之间，如果不传默认存储 1 天
     */
    @SerializedName("expireTime")
    private Long expireTime;

    private AddBatchPageRequest(Builder builder) {
        super(builder);
        this.productCode = builder.productCode;
        this.appCode = builder.appCode;
        this.level = builder.level;
        this.tags = builder.tags;
        this.content = builder.content;
        this.expireTime = builder.expireTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String productCode;
        private String appCode;
        private String level;
        private List<String> tags;
        private String content;
        private Long expireTime;

        public Builder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder expireTime(Long expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public AddBatchPageRequest build() {
            return new AddBatchPageRequest(this);
        }
    }

    public String getProductCode() {
        return productCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContent() {
        return content;
    }

    public Long getExpireTime() {
        return expireTime;
    }
}