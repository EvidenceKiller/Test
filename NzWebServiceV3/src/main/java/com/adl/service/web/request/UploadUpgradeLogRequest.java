package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class UploadUpgradeLogRequest {

    /**
     * 设备id
     */
    @SerializedName("deviceId")
    private String deviceId;

    /**
     * 升级计划id
     */
    @SerializedName("planId")
    private String planId;

    /**
     * 原因
     */
    @SerializedName("reason")
    private String reason;

    /**
     * 升级结果标识
     */
    @SerializedName("upgradeFlag")
    private String upgradeFlag;

    private UploadUpgradeLogRequest(Builder builder) {
        this.deviceId = builder.deviceId;
        this.planId = builder.planId;
        this.reason = builder.reason;
        this.upgradeFlag = builder.upgradeFlag;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String deviceId;
        private String planId;
        private String reason;
        private String upgradeFlag;

        public Builder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder planId(String planId) {
            this.planId = planId;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder upgradeFlag(String upgradeFlag) {
            this.upgradeFlag = upgradeFlag;
            return this;
        }

        public UploadUpgradeLogRequest build() {
            return new UploadUpgradeLogRequest(this);
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getPlanId() {
        return planId;
    }

    public String getReason() {
        return reason;
    }

    public String getUpgradeFlag() {
        return upgradeFlag;
    }
}