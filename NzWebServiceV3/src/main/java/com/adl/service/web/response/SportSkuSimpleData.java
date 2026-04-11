package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class SportSkuSimpleData {
    @SerializedName("sportSkuId")
    private String sportSkuId;

    @SerializedName("sportSkuName")
    private String sportSkuName;

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("levelType")
    private String levelType;

    @SerializedName("settlementType")
    private String settlementType;
}
