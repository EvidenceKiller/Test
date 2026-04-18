package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class UpgradePlanByDeviceWithOutLoginRequest extends BaseRequest {

    /** 路径参数 */
    @SerializedName("deviceId")
    private String deviceId;

    private UpgradePlanByDeviceWithOutLoginRequest(Builder builder) {
        super(builder);
        this.deviceId = builder.deviceId;
    }

    public static Builder builder(String deviceId) {
        return new Builder(deviceId);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String deviceId;
        Builder(String deviceId) {
            this.deviceId = deviceId;
        }

        public UpgradePlanByDeviceWithOutLoginRequest build() { return new UpgradePlanByDeviceWithOutLoginRequest(this); }
    }

}