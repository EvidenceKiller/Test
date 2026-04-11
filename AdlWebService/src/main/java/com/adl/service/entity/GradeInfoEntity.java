package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * "enableSort": false,
 * "moduleStr": "/tis/base/grade",
 * "id": "629500852c2149abbe6a8058e4924a7b",
 * "tenantId": "3d5063fb8dca42fea87985ee9ddefe90",
 * "orgId": "1861325708924096512",
 * "enterYear": 2022,
 * "graduationYear": 2025,
 * "gradeCode": "629500852c2149abbe6a8058e4924a7b",
 * "gradeName": "初中2022级",
 * "secCode": "2",
 * "departmentCodes": null,
 * "graduationFlag": 0,
 * "createTime": 1732611845000,
 * "updateTime": 1732611845000,
 * "classList":List<ClassInfoEntity>
 * "gradeNum": 9,
 * "stageType": "2",
 * "gradeChineseName": "九年级",
 * "gradeStageChineseName": "初三"
 */
public class GradeInfoEntity implements Serializable {
    private String id;
    private String tenantId;
    private String orgId;
    private int enterYear;
    private int graduationYear;
    private String gradeCode;
    private String gradeName;
    private String secCode;
    private int gradeNum;
    private String stageType;
    private String gradeChineseName;
    private String gradeStageChineseName;
    private List<GradeTreeClassInfoEntity> classList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(int enterYear) {
        this.enterYear = enterYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public String getGradeChineseName() {
        return gradeChineseName;
    }

    public void setGradeChineseName(String gradeChineseName) {
        this.gradeChineseName = gradeChineseName;
    }

    public String getGradeStageChineseName() {
        return gradeStageChineseName;
    }

    public void setGradeStageChineseName(String gradeStageChineseName) {
        this.gradeStageChineseName = gradeStageChineseName;
    }

    public List<GradeTreeClassInfoEntity> getClassList() {
        return classList;
    }

    public void setClassList(List<GradeTreeClassInfoEntity> classList) {
        this.classList = classList;
    }
}
