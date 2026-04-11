package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class SceneData {

    /**
     * 主键id
     */
    @SerializedName("id")
    private String id;

    /**
     * 场景标识码
     */
    @SerializedName("sceneCode")
    private String sceneCode;

    /**
     * 场景名称
     */
    @SerializedName("sceneName")
    private String sceneName;

    /**
     * 产经对应端 多个逗号隔开
     */
    @SerializedName("appCodes")
    private String appCodes;

    /**
     * 序号
     */
    @SerializedName("sceneSort")
    private Long sceneSort;

    /**
     * 场景类型 场景类型 1 锻炼 测试
     */
    @SerializedName("sceneType")
    private String sceneType;

    @SerializedName("enabled")
    private Boolean enabled;
}
