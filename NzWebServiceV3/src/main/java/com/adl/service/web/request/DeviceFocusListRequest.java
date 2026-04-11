package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class DeviceFocusListRequest {

    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("appCode")
    private String appCode;

    private DeviceFocusListRequest(Builder builder) {
        this.deviceId = builder.deviceId;
        this.appCode = builder.appCode;
    }

    public static Builder builder(String deviceId) {
        return new Builder(deviceId);
    }


    public static class Builder {
        private String deviceId;
        private String appCode;

        Builder(String deviceId) {
            this.deviceId = deviceId;
        }

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public DeviceFocusListRequest build() {
            return new DeviceFocusListRequest(this);
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getAppCode() {
        return appCode;
    }
}