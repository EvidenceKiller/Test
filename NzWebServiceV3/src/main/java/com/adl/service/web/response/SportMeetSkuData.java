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
public final class SportMeetSkuData {
    //  运动会运动sku标识码
    @SerializedName("id")
    private String id;
    //  运动会标识码
    @SerializedName("meetId")
    private String meetId;
    //  运动sku标识码
    @SerializedName("sportSkuId")
    private String sportSkuId;
    //  运动sku标识码
    @SerializedName("sportSkuName")
    private String sportSkuName;
    //  类型名称
    @SerializedName("sportTypeName")
    private String sportTypeName;

    //  精度方式 详见码表
    @SerializedName("accuracyMethod")
    private int accuracyMethod;
    //  精度类型 详见码表
    @SerializedName("accuracyType")
    private int accuracyType;
    //  报名人数
    @SerializedName("applyUserCount")
    private int applyUserCount;
    //  比赛方式
    @SerializedName("competitionType")
    private int competitionType;

    //  封面地址
    @SerializedName("coverUrl")
    private String coverUrl;
    //  结束类型 参考字典编码：adl_bms_sport_sku_end_type
    @SerializedName("endType")
    private int endType;
    //  结束方式 参考字典编码：adl_sport_end_unit
    @SerializedName("endValue")
    private int endValue;
    //  结束方式为其他时的备注
    @SerializedName("endRemark")
    private String endRemark;
    //  测量方式 参考字典编码：adl_bms_sport_sku_measure_mode
    @SerializedName("measureMode")
    private String measureMode;
    //  测量单位代码 参考字典编码：adl_sport_sport_unit
    @SerializedName("measureUnitCode")
    private String measureUnitCode;

    //  排序 低优策略
    @SerializedName("meetSkuSort")
    private int meetSkuSort;
    //  项目设置id
    @SerializedName("settingId")
    private String settingId;
    //  项目高低优 参考字典编码：adl_bms_sport_priority
    @SerializedName("sportPriority")
    private int sportPriority;

    //  测试人数
    @SerializedName("testCount")
    private int testCount;

    //
    @SerializedName("competitionNum")
    private int competitionNum;

    @SerializedName("appCodes")
    private List<String> appCodes;
}
