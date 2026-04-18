package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class DeviceQrcodeRequest extends BaseRequest {

    /**
     * 路径参数
     */
    @SerializedName("appCode")
    private String appCode;

    private DeviceQrcodeRequest(Builder builder) {
        super(builder);
        this.appCode = builder.appCode;
    }

    public static Builder builder(String appCode) {
        return new Builder(appCode);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String appCode;

        Builder(String appCode) {
            this.appCode = appCode;
        }

        public DeviceQrcodeRequest build() { return new DeviceQrcodeRequest(this); }
    }

    public String getAppCode() { return appCode; }
}