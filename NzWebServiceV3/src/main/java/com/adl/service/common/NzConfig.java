package com.adl.service.common;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2026/2/2
 * Describe   : 类描述
 */
public class NzConfig {

    private int httpConnTimeout;
    private int httpReadTimeout;
    //生效的配置
    private String effectiveConfig;
    // 重复上传次数
    private int againUploadTimes;
    // 本地文件保存时长
    private int localFileMaxSaveTime;
    // 空闲空间提醒
    private int freeSpaceNotice;
    // 是否本地化
    private boolean useLocalize;

    // 云平台配置
    private NzConfigNode saasDebug;
    private NzConfigNode saasRelease;

    // 本地环境配置
    private NzConfigNode localDebug;
    private NzConfigNode localRelease;

    public NzConfig() {

    }

    public int getHttpConnTimeout() {
        return httpConnTimeout;
    }

    public void setHttpConnTimeout(int httpConnTimeout) {
        this.httpConnTimeout = httpConnTimeout;
    }

    public int getHttpReadTimeout() {
        return httpReadTimeout;
    }

    public void setHttpReadTimeout(int httpReadTimeout) {
        this.httpReadTimeout = httpReadTimeout;
    }

    public String getEffectiveConfig() {
        return effectiveConfig;
    }

    public void setEffectiveConfig(String effectiveConfig) {
        this.effectiveConfig = effectiveConfig;
    }

    public int getAgainUploadTimes() {
        return againUploadTimes;
    }

    public void setAgainUploadTimes(int againUploadTimes) {
        this.againUploadTimes = againUploadTimes;
    }

    public int getLocalFileMaxSaveTime() {
        return localFileMaxSaveTime;
    }

    public void setLocalFileMaxSaveTime(int localFileMaxSaveTime) {
        this.localFileMaxSaveTime = localFileMaxSaveTime;
    }

    public int getFreeSpaceNotice() {
        return freeSpaceNotice;
    }

    public void setFreeSpaceNotice(int freeSpaceNotice) {
        this.freeSpaceNotice = freeSpaceNotice;
    }

    public boolean isUseLocalize() {
        return useLocalize;
    }

    public void setUseLocalize(boolean useLocalize) {
        this.useLocalize = useLocalize;
    }

    public NzConfigNode getSaasDebug() {
        return saasDebug;
    }

    public void setSaasDebug(NzConfigNode saasDebug) {
        this.saasDebug = saasDebug;
    }

    public NzConfigNode getSaasRelease() {
        return saasRelease;
    }

    public void setSaasRelease(NzConfigNode saasRelease) {
        this.saasRelease = saasRelease;
    }

    public NzConfigNode getLocalDebug() {
        return localDebug;
    }

    public void setLocalDebug(NzConfigNode localDebug) {
        this.localDebug = localDebug;
    }

    public NzConfigNode getLocalRelease() {
        return localRelease;
    }

    public void setLocalRelease(NzConfigNode localRelease) {
        this.localRelease = localRelease;
    }
}
