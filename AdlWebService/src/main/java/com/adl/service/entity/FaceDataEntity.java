package com.adl.service.entity;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public class FaceDataEntity implements Serializable {

    // 人脸特征值
    private String faceData;

    // 厂商类型 1：百度 2：商汤
    private String type;

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}