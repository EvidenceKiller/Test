package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class DeviceActiveRequest {

    /**
     * 激活码
     */
    @SerializedName("activeCode")
    private String activeCode;

    /**
     * 设备 ID
     */
    @SerializedName("deviceId")
    private String deviceId;

    private DeviceActiveRequest(Builder builder) {
        this.activeCode = builder.activeCode;
        this.deviceId = builder.deviceId;
    }

    public static Builder builder(String activeCode, String deviceId) {
        return new Builder(activeCode, deviceId);
    }


    public static class Builder {
        private String activeCode;
        private String deviceId;

        Builder(String activeCode, String deviceId) {
            this.activeCode = activeCode;
            this.deviceId = deviceId;
        }

        public DeviceActiveRequest build() {
            return new DeviceActiveRequest(this);
        }
    }

    public String getActiveCode() {
        return activeCode;
    }

    public String getDeviceId() {
        return deviceId;
    }
}