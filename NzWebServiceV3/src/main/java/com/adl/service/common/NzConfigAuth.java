package com.adl.service.common;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2026/2/2
 * Describe   : 类描述
 */
public class NzConfigAuth {

    private boolean enableDebug;
    private String authHost;
    private String busHost;
    private String authHostV3;
    private String busHostV3;
    private String deviceType;
    private String backGroundPath;
    private String logoPath;

    public NzConfigAuth() {

    }

    public boolean isEnableDebug() {
        return enableDebug;
    }

    public void setEnableDebug(boolean enableDebug) {
        this.enableDebug = enableDebug;
    }

    public String getAuthHost() {
        return authHost;
    }

    public void setAuthHost(String authHost) {
        this.authHost = authHost;
    }

    public String getBusHost() {
        return busHost;
    }

    public void setBusHost(String busHost) {
        this.busHost = busHost;
    }

    public String getAuthHostV3() {
        return authHostV3;
    }

    public void setAuthHostV3(String authHostV3) {
        this.authHostV3 = authHostV3;
    }

    public String getBusHostV3() {
        return busHostV3;
    }

    public void setBusHostV3(String busHostV3) {
        this.busHostV3 = busHostV3;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBackGroundPath() {
        return backGroundPath;
    }

    public void setBackGroundPath(String backGroundPath) {
        this.backGroundPath = backGroundPath;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
