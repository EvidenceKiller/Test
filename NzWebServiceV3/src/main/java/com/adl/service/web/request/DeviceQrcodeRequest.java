package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class DeviceQrcodeRequest {

    /** 路径参数 */
    @SerializedName("appCode")
    private String appCode;

    private DeviceQrcodeRequest(Builder builder) {
        this.appCode = builder.appCode;
    }

    public static Builder builder(String appCode) {
        return new Builder(appCode);
    }


    public static class Builder {
        private String appCode;
        Builder(String appCode) {
            this.appCode = appCode;
        }

        public DeviceQrcodeRequest build() { return new DeviceQrcodeRequest(this); }
    }

    public String getAppCode() { return appCode; }
}