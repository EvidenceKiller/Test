package com.adl.service.db.entity;

import com.adl.service.data.FaceData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class FaceEntity {

    // 人脸特征值
    private String faceData;

    // 厂商类型 1：百度 2：商汤
    private String type;

    public static FaceEntity convertToEntity(FaceData data) {
        if (data == null) {
            return null;
        }
        FaceEntity entity = new FaceEntity();
        entity.setFaceData(data.getFaceData());
        entity.setType(data.getType());
        return entity;
    }

    public static FaceData convertToData(FaceEntity entity) {
        if (entity == null) {
            return null;
        }
        FaceData data = new FaceData();
        data.setFaceData(entity.getFaceData());
        data.setType(entity.getType());
        return data;
    }
}
