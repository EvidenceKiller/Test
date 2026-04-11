package com.adl.service.entity;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 图片集
 */
public class SportImageEntity implements Serializable {

    //  图片相对地址
    private String imageUrl = "";

    //  运动分析图片说明
    private String motionAnalysisImageDesc;

    //  图片说明
    private String imageDesc = "";

    private String groupIndex;

    public SportImageEntity() {
    }

    public SportImageEntity(String imageUrl, String imageDesc) {
        this.imageUrl = imageUrl;
        this.imageDesc = imageDesc;
    }

    public SportImageEntity(String imageDesc, String imageUrl, String groupIndex) {
        this.imageUrl = imageUrl;
        this.imageDesc = imageDesc;
        this.groupIndex = groupIndex;
    }

    public SportImageEntity(String imageUrl, String imageDesc,String motionAnalysisImageDesc, String groupIndex) {
        this.imageUrl = imageUrl;
        this.imageDesc = imageDesc;
        this.groupIndex = groupIndex;
        this.motionAnalysisImageDesc = motionAnalysisImageDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getGroupIndex() {
        return groupIndex;
    }

    public String getMotionAnalysisImageDesc() {
        return motionAnalysisImageDesc;
    }

    public void setMotionAnalysisImageDesc(String motionAnalysisImageDesc) {
        this.motionAnalysisImageDesc = motionAnalysisImageDesc;
    }

    public void setGroupIndex(String groupIndex) {
        this.groupIndex = groupIndex;
    }

    public SportImageEntity toCopy() {
        SportImageEntity entity = new SportImageEntity();
        entity.setImageUrl(this.imageUrl);
        entity.setImageDesc(this.imageDesc);
        entity.setGroupIndex(this.groupIndex);
        entity.setMotionAnalysisImageDesc(this.motionAnalysisImageDesc);
        return entity;
    }
}
