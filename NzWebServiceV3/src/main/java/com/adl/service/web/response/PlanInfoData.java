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
public final class PlanInfoData {

    @SerializedName("planId")
    private String planId;

    @SerializedName("planName")
    private String planName;

    @SerializedName("standardType")
    private String standardType;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("standardId")
    private String standardId;

    @SerializedName("originStandardId")
    private String originStandardId;

    @SerializedName("sportList")
    private List<Object> sportList;

    @SerializedName("classIds")
    private List<Object> classIds;

    @SerializedName("classes")
    private List<Object> classes;

    @SerializedName("dayConfigsMap")
    private Object dayConfigsMap;

    @SerializedName("dateConfigsMap")
    private Object dateConfigsMap;

    @SerializedName("classAllFlag")
    private String classAllFlag;

    @SerializedName("status")
    private String status;

    /**
     * 总满分值
     */
    @SerializedName("totalFullCredit")
    private String totalFullCredit;

    /**
     * 总分计算方程式
     */
    @SerializedName("formula")
    private String formula;

    /**
     * 线上支付金额 线上支付金额，最低金额为0.01
     */
    @SerializedName("payPrice")
    private Double payPrice;

    /**
     * 批量下发标识位 0 否 1 是
     */
    @SerializedName("distributeFlag")
    private Boolean distributeFlag;

    /**
     * 全部学校标识位 0 全部 1 部分
     */
    @SerializedName("clazzAllFlag")
    private Boolean clazzAllFlag;

    /**
     * 报名标识位 0 不需要 1 需要
     */
    @SerializedName("signUpFlag")
    private Boolean signUpFlag;

    /**
     * 满分类型 参考字典编码：adl_tis_physical_test_plan_full_credit_type（1 项目满分 2 总满分）
     */
    @SerializedName("fullCreditType")
    private Integer fullCreditType;

    /**
     * 取整方式 参考字典编码：adl_tis_physical_test_plan_round（0 向上取整 1 向下取整 4 四舍五入）
     */
    @SerializedName("round")
    private Integer round;

    /**
     * 精度 参考字典编码：adl_tis_physical_test_plan_decimal_precision（0 整数 1 小数后一位 2 小数后两位 3 小数后三位 ）
     */
    @SerializedName("decimalPrecision")
    private Integer decimalPrecision;

    /**
     * 支付类型 参考字典编码：adl_tis_physical_test_plan_pay_type（1 免费 2 线下支付 3 线上支付（每人））
     */
    @SerializedName("payType")
    private Integer payType;

    /**
     * 自助测试 参考字典编码：adl_tis_physical_test_plan_self_test（0 非自助测试 1自助测试 ）
     */
    @SerializedName("selfTest")
    private Integer selfTest;

    @SerializedName("orgId")
    private String orgId;

    @SerializedName("delFlag")
    private Boolean delFlag;

    /**
     * 预约标识位 0 不需要 1 需要
     */
    @SerializedName("reserveFlag")
    private Boolean reserveFlag;

    /**
     * 计划结束时间
     */
    @SerializedName("reserveStartTime")
    private String reserveStartTime;

    /**
     * 计划开始时间
     */
    @SerializedName("reserveEndTime")
    private String reserveEndTime;

    /**
     * 两种模式的预约状态
     */
    @SerializedName("reserveStatusMap")
    private Object reserveStatusMap;
}
