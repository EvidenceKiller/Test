package com.adl.service.db.entity;

import com.adl.service.data.SportProjectData;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class SportProjectEntity implements Serializable {
    /**
     * 精度
     * 参考字典编码:adl_tis_physical_test_plan_decimal_precision
     * (0 整数 1 小数后一位 2 小数后两位 3 小数后三位)
     */
    private Integer decimalPrecision;

    /**
     * 项目分计算方程式
     */
    private String formula;

    /**
     * 取整方式
     * 参考字典编码:adl_tis_physical_test_plan_round
     * (0 向上取整 1 向下取整 4 四舍五入)
     */
    private Integer round;

    /**
     * 性别
     * 参考字典编码:adl_tis_sex
     */
    private String sex;

    /**
     * 权重值
     */
    private String weight;

    /**
     * 权重类型
     * 参考字典编码:adl_tis_physical_test_plan_arrake_project_weight_type
     * (1 必考 2 一类选考 3 二类选考)
     */
    private String weightType;

    /**
     * 运动附加标准id
     */
    private String addSportSkuId;

    /**
     * 项目id
     */
    private String sportSkuId;

    /**
     * 项目名称
     */
    private String sportSkuName;

    /**
     * 结算类型
     * 0仅分数 1仅等级 2分数和等级
     */
    private Integer settlementType;

    /**
     * 测量方式
     */
    private String measureMode;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 限制报告数量
     */
    private Integer limitReportCount;

    /**
     * 测量单位代码
     */
    private String measureUnitCode;

    public static SportProjectEntity convertToEntity(SportProjectData data) {
        if (data == null) {
            return null;
        }
        SportProjectEntity entity = new SportProjectEntity();
        entity.setDecimalPrecision(data.getDecimalPrecision());
        entity.setFormula(data.getFormula());
        entity.setRound(data.getRound());
        entity.setSex(data.getSex());
        entity.setWeight(data.getWeight());
        entity.setWeightType(data.getWeightType());
        entity.setAddSportSkuId(data.getAddSportSkuId());
        entity.setSportSkuId(data.getSportSkuId());
        entity.setSportSkuName(data.getSportSkuName());
        entity.setSettlementType(data.getSettlementType());
        entity.setMeasureMode(data.getMeasureMode());
        entity.setUnitName(data.getUnitName());
        entity.setLimitReportCount(data.getLimitReportCount());
        entity.setMeasureUnitCode(data.getMeasureUnitCode());
        return entity;
    }

    public static SportProjectData convertToData(SportProjectEntity entity) {
        if (entity == null) {
            return null;
        }
        SportProjectData data = new SportProjectData();
        data.setDecimalPrecision(entity.getDecimalPrecision());
        data.setFormula(entity.getFormula());
        data.setRound(entity.getRound());
        data.setSex(entity.getSex());
        data.setWeight(entity.getWeight());
        data.setWeightType(entity.getWeightType());
        data.setAddSportSkuId(entity.getAddSportSkuId());
        data.setSportSkuId(entity.getSportSkuId());
        data.setSportSkuName(entity.getSportSkuName());
        data.setSettlementType(entity.getSettlementType());
        data.setMeasureMode(entity.getMeasureMode());
        data.setUnitName(entity.getUnitName());
        data.setLimitReportCount(entity.getLimitReportCount());
        data.setMeasureUnitCode(entity.getMeasureUnitCode());
        return data;
    }
}
