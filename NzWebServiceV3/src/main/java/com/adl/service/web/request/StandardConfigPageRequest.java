package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class StandardConfigPageRequest extends BasePageRequest {

    /** 测试标准标识码 */
    @SerializedName("standardId")
    private String standardId;

    /** 运动项目标识码 */
    @SerializedName("sportProjectCode")
    private String sportProjectCode;

    /** 测试类型 */
    @SerializedName("standardType")
    private String standardType;

    private StandardConfigPageRequest(Builder builder) {
        super(builder);
        this.standardId = builder.standardId;
        this.sportProjectCode = builder.sportProjectCode;
        this.standardType = builder.standardType;
    }

    public static Builder builder(String standardType) {
        return new Builder(standardType);
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String standardId;
        private String sportProjectCode;
        private String standardType;
        Builder(String standardType) {
            this.standardType = standardType;
        }
        public Builder standardId(String standardId) {
            this.standardId = standardId;
            return this;
        }
        public Builder sportProjectCode(String sportProjectCode) {
            this.sportProjectCode = sportProjectCode;
            return this;
        }

        public StandardConfigPageRequest build() { return new StandardConfigPageRequest(this); }
    }
    public String getStandardId() { return standardId; }
    public String getSportProjectCode() { return sportProjectCode; }
    public String getStandardType() { return standardType; }
}