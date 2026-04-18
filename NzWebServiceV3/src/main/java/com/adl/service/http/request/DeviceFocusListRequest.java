package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class DeviceFocusListRequest extends BaseRequest {

    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("appCode")
    private String appCode;

    private DeviceFocusListRequest(Builder builder) {
        super(builder);
        this.deviceId = builder.deviceId;
        this.appCode = builder.appCode;
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
}