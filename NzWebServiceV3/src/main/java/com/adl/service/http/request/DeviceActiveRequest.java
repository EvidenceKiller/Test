package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class DeviceActiveRequest extends BaseRequest {

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
        super(builder);
        this.activeCode = builder.activeCode;
        this.deviceId = builder.deviceId;
    }

    public static Builder builder(String activeCode, String deviceId) {
        return new Builder(activeCode, deviceId);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        private String activeCode;
        private String deviceId;

        Builder(String activeCode, String deviceId) {
            this.activeCode = activeCode;
            this.deviceId = deviceId;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public DeviceActiveRequest build() {
            return new DeviceActiveRequest(this);
        }
    }
}