package com.adl.service.outer;

import com.adl.service.web.constants.Platform;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public class ConfigService {

    private boolean mDebug;
    private String mHost;
    private String mQiNiuHost;
    private String ossConfigHost;
    private String ossTokenHost;
    private String mQiNiuBucket;
    private String mSdcardRoot;
    private String mFacePrefix;
    //  平台(k12、gx)
    private Platform mPlatform = Platform.K12;
    // 读取本地配置文件
    private boolean readConfig;

    ConfigService() {
    }

    public boolean isDebug() {
        return mDebug;
    }

    public void setDebug(boolean mDebug) {
        this.mDebug = mDebug;
    }

    public String getHost() {
        return mHost;
    }

    public void setHost(String host) {
        this.mHost = host;
    }

    public String getQiNiuHost() {
        return mQiNiuHost;
    }

    public void setQiNiuHost(String host) {
        this.mQiNiuHost = host;
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

    public String getQiNiuBucket() {
        return mQiNiuBucket;
    }

    public void setQiNiuBucket(String bucket) {
        this.mQiNiuBucket = bucket;
    }

    public String getSdcardRoot() {
        return mSdcardRoot;
    }

    public void setSdcardRoot(String mSdcardRoot) {
        this.mSdcardRoot = mSdcardRoot;
    }

    public String getFacePrefix() {
        return mFacePrefix;
    }

    public void setFacePrefix(String mFacePrefix) {
        this.mFacePrefix = mFacePrefix;
    }

    public Platform getPlatform() {
        return mPlatform;
    }

    public void setPlatform(Platform mPlatform) {
        this.mPlatform = mPlatform;
    }

    public boolean isReadConfig() {
        return readConfig;
    }

    public void setReadConfig(boolean readConfig) {
        this.readConfig = readConfig;
    }
}
