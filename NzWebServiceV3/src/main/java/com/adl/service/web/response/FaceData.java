package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class FaceData {

    // 人脸特征值
    private String faceData;

    // 厂商类型 1：百度 2：商汤
    private String type;
}