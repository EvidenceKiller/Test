package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseSportPlan {
    /**
     * 组织测试计划ID
     */
    @SerializedName("ptPlanId")
    private String ptPlanId;

    /**
     * 组织测试标准ID
     */
    @SerializedName("ptStandardId")
    private String ptStandardId;

    /**
     * 组织测试标准类型
     */
    @SerializedName("ptStandardType")
    private String ptStandardType;

    /**
     * 组织测试批次
     */
    @SerializedName("ptBatch")
    private Integer ptBatch;

    /**
     * 组织测试运动标准分
     */
    @SerializedName("ptSportStandardScore")
    private String ptSportStandardScore;

    /**
     * 组织测试运动标准等级
     */
    @SerializedName("ptSportStandardLevel")
    private String ptSportStandardLevel;

    /**
     * 组织测试运动单项得分
     */
    @SerializedName("ptSportScore")
    private String ptSportScore;

    /**
     * 组织测试运动单项附加分
     */
    @SerializedName("ptSportStandardAddScore")
    private String ptSportStandardAddScore;

    /**
     * 是否违规
     */
    @SerializedName("sportViolationStatus")
    private Integer sportViolationStatus;

    /**
     * 运动违规次数
     */
    @SerializedName("sportViolationNumber")
    private Integer sportViolationNumber;

    /**
     * 组织测试运动成绩加减结果
     */
    @SerializedName("ptExtrasResult")
    private String ptExtrasResult;

    /**
     * 组织测试运动成绩加减模式 (+/-)
     */
    @SerializedName("ptExtrasResultMod")
    private String ptExtrasResultMod;

    /**
     * 组织测试运动加减分数
     */
    @SerializedName("ptExtrasScore")
    private String ptExtrasScore;

    /**
     * 组织测试运动分数加减模式 (+/-)
     */
    @SerializedName("ptExtrasScoreMod")
    private String ptExtrasScoreMod;

    /**
     * 测试类型 0:校方测试 1:自助测试
     */
    @SerializedName("ptTestType")
    private Integer ptTestType;
}
