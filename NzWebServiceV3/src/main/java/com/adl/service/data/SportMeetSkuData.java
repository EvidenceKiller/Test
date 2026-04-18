package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class SportMeetSkuData {
    @SerializedName("accuracyMethod")
    private Integer accuracyMethod;

    @SerializedName("accuracyType")
    private Integer accuracyType;

    @SerializedName("applyUserCount")
    private Integer applyUserCount;

    @SerializedName("classList")
    private List<SportMeetSkuSimpleClassData> classList;

    @SerializedName("competitionType")
    private Integer competitionType;

    @SerializedName("coverUrl")
    private String coverUrl;

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("endRemark")
    private String endRemark;

    @SerializedName("endType")
    private Integer endType;

    @SerializedName("endValue")
    private Integer endValue;

    @SerializedName("id")
    private String id;

    @SerializedName("measureMode")
    private String measureMode;

    @SerializedName("measureUnitCode")
    private String measureUnitCode;

    @SerializedName("meetId")
    private String meetId;

    @SerializedName("meetSkuSort")
    private Integer meetSkuSort;

    @SerializedName("settingId")
    private String settingId;

    @SerializedName("sportPriority")
    private Integer sportPriority;

    @SerializedName("sportSkuId")
    private String sportSkuId;

    @SerializedName("sportSkuName")
    private String sportSkuName;

    @SerializedName("sportTypeName")
    private String sportTypeName;

    @SerializedName("testCount")
    private Integer testCount;

    @SerializedName("updateTime")
    private String updateTime;

    @SerializedName("appCodes")
    private List<String> appCodes;

    @SerializedName("competitionNum")
    private Integer competitionNum;

    @SerializedName("limitFemale")
    private Integer limitFemale;

    @SerializedName("limitMale")
    private Integer limitMale;

    @SerializedName("updateFlag")
    private Boolean updateFlag;

    @SerializedName("limitTeam")
    private Integer limitTeam;
}
