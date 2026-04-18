package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class SoftwareReportRequest extends BaseRequest {

    /**
     * 产品代码
     */
    @SerializedName("productCode")
    private String productCode;

    /**
     * 应用代码
     */
    @SerializedName("appCode")
    private String appCode;

    /**
     * 软件版本
     */
    @SerializedName("softwareVersion")
    private String softwareVersion;

    /**
     * 包名
     */
    @SerializedName("packageName")
    private String packageName;

    /**
     * 设备昵称
     */
    @SerializedName("deviceNickname")
    private String deviceNickname;

    /**
     * 备注
     */
    @SerializedName("remark")
    private String remark;

    private SoftwareReportRequest(Builder builder) {
        super(builder);
        this.productCode = builder.productCode;
        this.appCode = builder.appCode;
        this.softwareVersion = builder.softwareVersion;
        this.packageName = builder.packageName;
        this.deviceNickname = builder.deviceNickname;
        this.remark = builder.remark;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String productCode;
        private String appCode;
        private String softwareVersion;
        private String packageName;
        private String deviceNickname;
        private String remark;

        public Builder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public Builder softwareVersion(String softwareVersion) {
            this.softwareVersion = softwareVersion;
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder deviceNickname(String deviceNickname) {
            this.deviceNickname = deviceNickname;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public SoftwareReportRequest build() {
            return new SoftwareReportRequest(this);
        }
    }
}