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
public final class SportProjectData {
    /**
     * 精度
     * 参考字典编码:adl_tis_physical_test_plan_decimal_precision
     * (0 整数 1 小数后一位 2 小数后两位 3 小数后三位)
     */
    @SerializedName("decimalPrecision")
    private Integer decimalPrecision;

    /**
     * 项目分计算方程式
     */
    @SerializedName("formula")
    private String formula;

    /**
     * 取整方式
     * 参考字典编码:adl_tis_physical_test_plan_round
     * (0 向上取整 1 向下取整 4 四舍五入)
     */
    @SerializedName("round")
    private Integer round;

    /**
     * 性别
     * 参考字典编码:adl_tis_sex
     */
    @SerializedName("sex")
    private String sex;

    /**
     * 权重值
     */
    @SerializedName("weight")
    private String weight;

    /**
     * 权重类型
     * 参考字典编码:adl_tis_physical_test_plan_arrake_project_weight_type
     * (1 必考 2 一类选考 3 二类选考)
     */
    @SerializedName("weightType")
    private String weightType;

    /**
     * 运动附加标准id
     */
    @SerializedName("addSportSkuId")
    private String addSportSkuId;

    /**
     * 项目id
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 项目名称
     */
    @SerializedName("sportSkuName")
    private String sportSkuName;

    /**
     * 结算类型
     * 0仅分数 1仅等级 2分数和等级
     */
    @SerializedName("settlementType")
    private Integer settlementType;

    /**
     * 测量方式
     */
    @SerializedName("measureMode")
    private String measureMode;

    /**
     * 单位名称
     */
    @SerializedName("unitName")
    private String unitName;

    /**
     * 限制报告数量
     */
    @SerializedName("limitReportCount")
    private Integer limitReportCount;

    /**
     * 测量单位代码
     */
    @SerializedName("measureUnitCode")
    private String measureUnitCode;
}
