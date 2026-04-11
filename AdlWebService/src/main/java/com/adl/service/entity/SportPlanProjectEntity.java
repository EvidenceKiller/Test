package com.adl.service.entity;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public class SportPlanProjectEntity implements Serializable {

    // 运动项目标识码
    private String sportProjectCode;

    // 运动项目名称
    private String sportProjectName;

    // 项目分计算方程式
    private String formula;

    // (0 整数 1 小数后一位 2 小数后两位 3 小数后三位 ）精度
    private String decimalPrecision;

    // （0 向上取整 1 向下取整 4 四舍五入）取整方式
    private String round;

    // 权重值
    private String weight;

    // 权重类型 0必考 1一类选考 2二类选考
    private String weightType;

    // 测试对象性别 1男性 2女性 3全部
    private String sex;

    private int testCount;

    public String getSportProjectCode() {
        return sportProjectCode;
    }

    public void setSportProjectCode(String sportProjectCode) {
        this.sportProjectCode = sportProjectCode;
    }

    public String getSportProjectName() {
        return sportProjectName;
    }

    public void setSportProjectName(String sportProjectName) {
        this.sportProjectName = sportProjectName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDecimalPrecision() {
        return decimalPrecision;
    }

    public void setDecimalPrecision(String decimalPrecision) {
        this.decimalPrecision = decimalPrecision;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }
}
