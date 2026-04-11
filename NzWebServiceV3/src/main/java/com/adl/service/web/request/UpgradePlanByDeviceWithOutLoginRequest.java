package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class UpgradePlanByDeviceWithOutLoginRequest {

    /** 路径参数 */
    @SerializedName("deviceId")
    private String deviceId;

    private UpgradePlanByDeviceWithOutLoginRequest(Builder builder) {
        this.deviceId = builder.deviceId;
    }

    public static Builder builder(String deviceId) {
        return new Builder(deviceId);
    }


    public static class Builder {
        private String deviceId;
        Builder(String deviceId) {
            this.deviceId = deviceId;
        }

        public UpgradePlanByDeviceWithOutLoginRequest build() { return new UpgradePlanByDeviceWithOutLoginRequest(this); }
    }

    public String getDeviceId() { return deviceId; }
}