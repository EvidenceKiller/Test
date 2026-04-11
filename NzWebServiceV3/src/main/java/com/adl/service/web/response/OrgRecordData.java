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
public final class OrgRecordData {

    @SerializedName("accountId")
    private String accountId;

    @SerializedName("classId")
    private String classId;

    @SerializedName("gradeId")
    private String gradeId;

    @SerializedName("sportSkuId")
    private String sportSkuId;

    @SerializedName("appCode")
    private String appCode;

    @SerializedName("orgId")
    private String orgId;

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
