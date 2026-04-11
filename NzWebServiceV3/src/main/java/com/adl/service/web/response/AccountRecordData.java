package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class AccountRecordData {

    @SerializedName("enableSort")
    private Boolean enableSort;

    @SerializedName("moduleStr")
    private String moduleStr;

    @SerializedName("classId")
    private String classId;

    @SerializedName("sportSkuId")
    private String sportSkuId;

    @SerializedName("accountId")
    private String accountId;

    @SerializedName("appCode")
    private String appCode;

    @SerializedName("sceneCode")
    private String sceneCode;

    @SerializedName("sportSceneCode")
    private String sportSceneCode;

    @SerializedName("semesterId")
    private String semesterId;

    @SerializedName("sportItemCode")
    private String sportItemCode;

    @SerializedName("sportTypeCode")
    private String sportTypeCode;

    @SerializedName("ptPlanId")
    private String ptPlanId;

}
