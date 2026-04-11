package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class CompetitionRankDetailItemData {
    @SerializedName("id")
    private String id;
    @SerializedName("sportSkuId")
    private String sportSkuId;
    @SerializedName("sportSkuName")
    private String sportSkuName;
    @SerializedName("sportNatureCode")
    private String sportNatureCode;
    @SerializedName("endType")
    private Integer endType;
    @SerializedName("endValue")
    private String endValue;
    @SerializedName("endRemark")
    private String endRemark;
    @SerializedName("measureUnitCode")
    private String measureUnitCode;
    @SerializedName("sportTypeCode")
    private String sportTypeCode;
    @SerializedName("sportItemCode")
    private String sportItemCode;
    @SerializedName("sportTypeName")
    private String sportTypeName;
    @SerializedName("sportResult")
    private String sportResult;
    @SerializedName("sceneCode")
    private String sceneCode;
    @SerializedName("iconUrl")
    private String iconUrl;
}
