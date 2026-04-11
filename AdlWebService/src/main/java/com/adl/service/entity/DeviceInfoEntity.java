package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class DeviceInfoEntity implements Serializable {

    private String id;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 产品code
     */
    private String productCode;

    /**
     * appCode
     */
    private String appCode;

    /**
     * 软件版本
     */
    private String softwareVersion;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 设备别称
     */
    private String deviceNickname;

    /**
     * currentIpAddress
     */
    private String ipAddress;

    /**
     * 备注
     */
    private String remark;

    /**
     * createTime
     */
    private String createTime;

    /**
     * updateTime
     */
    private String updateTime;

    /**
     * 设备绑定机构激活时间
     */
    private String activeTime;

    /**
     * currentSoftwareVersion
     */
    private String currentSoftwareVersion;

    /**
     * 删除标识位 0 可用 1 删除
     */
    private Boolean delFlag;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 固件版本
     */
    private String firmwareVersion;

    /**
     * 硬件型号
     */
    private String hardwareModel;

    /**
     * 硬件SN序列号
     */
    private String hardwareSn;

    /**
     * 设备类型 参考字典编码：adl_bms_device_type
     */
    private String hardwareType;

    /**
     * mac地址
     */
    private String macAddress;

    /**
     * mqttConnectStatus
     */
    private Boolean mqttConnectStatus;

    /**
     * 机构id
     */
    private String orgId;

    /**
     * orgName
     */
    private String orgName;

    /**
     * 场所id
     */
    private String placeId;

    /**
     * 场所纬度
     */
    private String placeLat;

    /**
     * 场所经度
     */
    private String placeLon;

    /**
     * 场所名称
     */
    private String placeName;

    /**
     * 场所类型
     */
    private String placeType;

    /**
     * 租户id
     */
    private String tenantOrgId;

    /**
     * deviceApps
     */
    private List<DeviceApp> deviceApps;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDeviceNickname() {
        return deviceNickname;
    }

    public void setDeviceNickname(String deviceNickname) {
        this.deviceNickname = deviceNickname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getCurrentSoftwareVersion() {
        return currentSoftwareVersion;
    }

    public void setCurrentSoftwareVersion(String currentSoftwareVersion) {
        this.currentSoftwareVersion = currentSoftwareVersion;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getHardwareModel() {
        return hardwareModel;
    }

    public void setHardwareModel(String hardwareModel) {
        this.hardwareModel = hardwareModel;
    }

    public String getHardwareSn() {
        return hardwareSn;
    }

    public void setHardwareSn(String hardwareSn) {
        this.hardwareSn = hardwareSn;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Boolean getMqttConnectStatus() {
        return mqttConnectStatus;
    }

    public void setMqttConnectStatus(Boolean mqttConnectStatus) {
        this.mqttConnectStatus = mqttConnectStatus;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceLat() {
        return placeLat;
    }

    public void setPlaceLat(String placeLat) {
        this.placeLat = placeLat;
    }

    public String getPlaceLon() {
        return placeLon;
    }

    public void setPlaceLon(String placeLon) {
        this.placeLon = placeLon;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getTenantOrgId() {
        return tenantOrgId;
    }

    public void setTenantOrgId(String tenantOrgId) {
        this.tenantOrgId = tenantOrgId;
    }

    public List<DeviceApp> getDeviceApps() {
        return deviceApps;
    }

    public void setDeviceApps(List<DeviceApp> deviceApps) {
        this.deviceApps = deviceApps;
    }

    /**
     * 设备应用信息
     */
    public static class DeviceApp {
        /**
         * 应用code
         */
        private String appCode;

        /**
         * appName
         */
        private String appName;

        /**
         * createTime
         */
        private String createTime;

        /**
         * 删除标识位 0 可用 1 删除
         */
        private Boolean delFlag;

        /**
         * 设备id
         */
        private String deviceId;

        /**
         * 设备别称
         */
        private String deviceNickname;

        /**
         * 设备类型 DEVICE（默认） 设备 COMPONENT 部件
         */
        private String deviceType;

        /**
         * id
         */
        private String id;

        /**
         * 包名
         */
        private String packageName;

        /**
         * 产品code
         */
        private String productCode;

        /**
         * 备注
         */
        private String remark;

        /**
         * 软件版本
         */
        private String softwareVersion;

        /**
         * updateTime
         */
        private String updateTime;

        // Getters and Setters for DeviceApp fields

        public String getAppCode() {
            return appCode;
        }

        public void setAppCode(String appCode) {
            this.appCode = appCode;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Boolean getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Boolean delFlag) {
            this.delFlag = delFlag;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceNickname() {
            return deviceNickname;
        }

        public void setDeviceNickname(String deviceNickname) {
            this.deviceNickname = deviceNickname;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSoftwareVersion() {
            return softwareVersion;
        }

        public void setSoftwareVersion(String softwareVersion) {
            this.softwareVersion = softwareVersion;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

}
