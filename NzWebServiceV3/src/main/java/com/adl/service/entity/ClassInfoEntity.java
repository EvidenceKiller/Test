package com.adl.service.entity;

import java.io.Serializable;
/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public class ClassInfoEntity implements Serializable {

    // 班级Id
    private String classId;

    // 班级简称
    private String className;

    // 班级全称
    private String classFullName;

    private String gradeName;

    private int gradeNum;

    // 1:班主任 2：任课老师
    private int relType;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public int getRelType() {
        return relType;
    }

    public void setRelType(int relType) {
        this.relType = relType;
    }
}