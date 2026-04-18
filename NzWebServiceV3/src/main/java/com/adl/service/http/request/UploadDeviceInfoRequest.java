package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class UploadDeviceInfoRequest extends BaseRequest {

    /**
     * 设备Id
     */
    @SerializedName("id")
    private String id;

    /**
     * 设备名称
     */
    @SerializedName("deviceName")
    private String deviceName;

    /**
     * mac地址
     */
    @SerializedName("macAddress")
    private String macAddress;

    private UploadDeviceInfoRequest(Builder builder) {
        super(builder);
        this.id = builder.id;
        this.deviceName = builder.deviceName;
        this.macAddress = builder.macAddress;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
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

        public UploadDeviceInfoRequest build() {
            return new UploadDeviceInfoRequest(this);
        }
    }
}