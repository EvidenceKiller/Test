package com.adl.service.entity;

import androidx.room.ColumnInfo;

public class StudentClassResult {

    @ColumnInfo(name = "_grade_num")
    private String gradeNum;

    @ColumnInfo(name = "_class_num")
    private String classNum;

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
