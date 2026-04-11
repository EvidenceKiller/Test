package com.adl.service.common;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2026/2/2
 * Describe   : 类描述
 */
public class NzConfigBusiness {

    private boolean enableDebug;
    private String rootPath;
    private String busHost;
    private String busHostV3;
    private String ossType;
    private String busType;
    private String qnHost;
    private String qnBucket;
    private String qnPrefix;
    private String ossConfigHost;
    private String ossTokenHost;
    private String ossBucket;

    public NzConfigBusiness() {

    }

    public boolean isEnableDebug() {
        return enableDebug;
    }

    public void setEnableDebug(boolean enableDebug) {
        this.enableDebug = enableDebug;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getBusHost() {
        return busHost;
    }

    public void setBusHost(String busHost) {
        this.busHost = busHost;
    }

    public String getBusHostV3() {
        return busHostV3;
    }

    public void setBusHostV3(String busHostV3) {
        this.busHostV3 = busHostV3;
    }

    public String getOssType() {
        return ossType;
    }

    public void setOssType(String ossType) {
        this.ossType = ossType;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getQnHost() {
        return qnHost;
    }

    public void setQnHost(String qnHost) {
        this.qnHost = qnHost;
    }

    public String getQnBucket() {
        return qnBucket;
    }

    public void setQnBucket(String qnBucket) {
        this.qnBucket = qnBucket;
    }

    public String getQnPrefix() {
        return qnPrefix;
    }

    public void setQnPrefix(String qnPrefix) {
        this.qnPrefix = qnPrefix;
    }

    public String getOssConfigHost() {
        return ossConfigHost;
    }

    public void setOssConfigHost(String ossConfigHost) {
        this.ossConfigHost = ossConfigHost;
    }

    public String getOssTokenHost() {
        return ossTokenHost;
    }

    public void setOssTokenHost(String ossTokenHost) {
        this.ossTokenHost = ossTokenHost;
    }

    public String getOssBucket() {
        return ossBucket;
    }

    public void setOssBucket(String ossBucket) {
        this.ossBucket = ossBucket;
    }
}
