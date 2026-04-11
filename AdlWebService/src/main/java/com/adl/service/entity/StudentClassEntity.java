package com.adl.service.entity;

import java.io.Serializable;

/**
 * Describe   :
 * Author     : zhoumiqi
 * Date       : 2025/2/14
 */
public class StudentClassEntity implements Serializable {
    /**
     * 班级全称 eg:小学一年级一班
     */
    private String classFullName;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 班级代码 1 2 3 4 5
     */
    private int classNum;
    /**
     * 年级编码
     */
    private String gradeCode;
    /**
     * 班级id
     */
    private String id;

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
