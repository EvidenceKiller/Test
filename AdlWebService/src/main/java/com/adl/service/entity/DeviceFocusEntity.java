package com.adl.service.entity;

import java.io.Serializable;

public class DeviceFocusEntity implements Serializable {
    /**
     * "enableSort": false,
     * "moduleStr": "/gms/device/focus",
     * "id": "895016a67c94043e869b0d8f6d2cb1e8",
     * "deviceId": "673dac94d2af9091daa0585b46ffc092",
     * "appCode": "101201",
     * "sceneCode": "2002",
     * "busiId": "fa3e9a01c586468a9667af41d32df949",
     * "timeType": 0,
     * "startTime": 1755792000000,
     * "endTime": 1757087999000,
     * "deviceIds": null,
     * "orgId": null,
     * "busiName": "搞笑测试"
     */
    private boolean enableSort;
    private String moduleStr;
    private String id;
    private String deviceId;
    private String appCode;
    private String sceneCode;
    private String busiId;
    private String timeType;
    private long startTime;
    private long endTime;
    private String orgId;
    private String busiName;

    public boolean isEnableSort() {
        return enableSort;
    }

    public void setEnableSort(boolean enableSort) {
        this.enableSort = enableSort;
    }

    public String getModuleStr() {
        return moduleStr;
    }

    public void setModuleStr(String moduleStr) {
        this.moduleStr = moduleStr;
    }

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

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }
}
