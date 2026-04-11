package com.adl.service.entity;

import java.io.Serializable;

public class BannerEntity implements Serializable {
    //  主键id
    private String id;
    //  目标id
    private String targetId;
    //  banner标识码
    private String bannerId;

    private String createTime;
    private String updateTime;

    //  目标id
    private String targetIds;
    //  图片路径
    private String imageUrl;
    //  目标层级id 目标层级id
    private String targetType;
    //  排序
    private int bannerSort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
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

    public String getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(String targetIds) {
        this.targetIds = targetIds;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public int getBannerSort() {
        return bannerSort;
    }

    public void setBannerSort(int bannerSort) {
        this.bannerSort = bannerSort;
    }
}
