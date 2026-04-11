package com.adl.service.entity;

import java.io.Serializable;

public class CompetitionItemEntity implements Serializable {

    private String id;
    private String sportSkuId;
    private String sportSkuName;
    private String sportNatureCode;
    private int endType;
    private String endValue;
    private String endRemark;
    private String measureUnitCode;
    private String sportTypeCode;
    private String sportItemCode;
    private String sportTypeName;
    private String sportResult;
    private String sceneCode;
    private String iconUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

    public String getSportSkuName() {
        return sportSkuName;
    }

    public void setSportSkuName(String sportSkuName) {
        this.sportSkuName = sportSkuName;
    }

    public String getSportNatureCode() {
        return sportNatureCode;
    }

    public void setSportNatureCode(String sportNatureCode) {
        this.sportNatureCode = sportNatureCode;
    }

    public int getEndType() {
        return endType;
    }

    public void setEndType(int endType) {
        this.endType = endType;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }

    public String getEndRemark() {
        return endRemark;
    }

    public void setEndRemark(String endRemark) {
        this.endRemark = endRemark;
    }

    public String getMeasureUnitCode() {
        return measureUnitCode;
    }

    public void setMeasureUnitCode(String measureUnitCode) {
        this.measureUnitCode = measureUnitCode;
    }

    public String getSportTypeCode() {
        return sportTypeCode;
    }

    public void setSportTypeCode(String sportTypeCode) {
        this.sportTypeCode = sportTypeCode;
    }

    public String getSportItemCode() {
        return sportItemCode;
    }

    public void setSportItemCode(String sportItemCode) {
        this.sportItemCode = sportItemCode;
    }

    public String getSportTypeName() {
        return sportTypeName;
    }

    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
