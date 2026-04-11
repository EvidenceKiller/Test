package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class UploadDeviceNameRequest {

    /**
     * 设备 Id
     */
    @SerializedName("id")
    private String id;

    /**
     * 设备名称
     */
    @SerializedName("deviceName")
    private String deviceName;

    /**
     * mac 地址
     */
    @SerializedName("macAddress")
    private String macAddress;

    private UploadDeviceNameRequest(Builder builder) {
        this.id = builder.id;
        this.deviceName = builder.deviceName;
        this.macAddress = builder.macAddress;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }


    public static class Builder {
        private String id;
        private String deviceName;
        private String macAddress;

        Builder(String id) {
            this.id = id;
        }

        public Builder deviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public Builder macAddress(String macAddress) {
            this.macAddress = macAddress;
            return this;
        }

        public UploadDeviceNameRequest build() {
            return new UploadDeviceNameRequest(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getMacAddress() {
        return macAddress;
    }
}