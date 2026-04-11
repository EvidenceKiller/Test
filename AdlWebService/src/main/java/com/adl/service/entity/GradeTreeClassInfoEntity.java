package com.adl.service.entity;

import java.io.Serializable;

/**
 * "enableSort": false,
 * "moduleStr": "/tis/base/class",
 * "id": "1861342835336876032",
 * "tenantId": "3d5063fb8dca42fea87985ee9ddefe90",
 * "orgId": "1861325708924096512",
 * "gradeCode": "629500852c2149abbe6a8058e4924a7b",
 * "classNum": 10,
 * "className": "10班",
 * "classFullName": "初中2022级10班",
 * "departmentCode": null,
 * "status": 1,
 * "createTime": 1732613688000,
 * "updateTime": 1732613688000,
 * "gradeStageChineseName": null
 */
public class GradeTreeClassInfoEntity implements Serializable {
    private String id;
    private String tenantId;
    private String orgId;
    private String gradeCode;
    private int classNum;
    private String className;
    private String classFullName;
    private int status;
    private int select;

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

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }
}
