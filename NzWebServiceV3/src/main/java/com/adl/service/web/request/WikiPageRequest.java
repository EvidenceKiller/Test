package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class WikiPageRequest extends BasePageRequest {

    /** 标题 */
    @SerializedName("keyword")
    private String keyword;

    /** 内容形式 */
    @SerializedName("wikiType")
    private String wikiType;

    /** 分类ID */
    @SerializedName("wikiTypeId")
    private String wikiTypeId;

    /** 状态 */
    @SerializedName("enabled")
    private String enabled;

    /** 应用编码 */
    @SerializedName("appCode")
    private String appCode;

    /** 机构id */
    @SerializedName("orgId")
    private String orgId;

    private WikiPageRequest(Builder builder) {
        super(builder);
        this.keyword = builder.keyword;
        this.wikiType = builder.wikiType;
        this.wikiTypeId = builder.wikiTypeId;
        this.enabled = builder.enabled;
        this.appCode = builder.appCode;
        this.orgId = builder.orgId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String keyword;
        private String wikiType;
        private String wikiTypeId;
        private String enabled;
        private String appCode;
        private String orgId;
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }
        public Builder wikiType(String wikiType) {
            this.wikiType = wikiType;
            return this;
        }
        public Builder wikiTypeId(String wikiTypeId) {
            this.wikiTypeId = wikiTypeId;
            return this;
        }
        public Builder enabled(String enabled) {
            this.enabled = enabled;
            return this;
        }
        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public WikiPageRequest build() { return new WikiPageRequest(this); }
    }
    public String getKeyword() { return keyword; }
    public String getWikiType() { return wikiType; }
    public String getWikiTypeId() { return wikiTypeId; }
    public String getEnabled() { return enabled; }
    public String getAppCode() { return appCode; }
    public String getOrgId() { return orgId; }
}