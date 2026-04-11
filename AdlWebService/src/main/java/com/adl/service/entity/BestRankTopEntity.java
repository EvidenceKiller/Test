package com.adl.service.entity;

import java.io.Serializable;

public class BestRankTopEntity implements Serializable {

    //  用户标识码
    private String accountId;
    //  用户名
    private String accountName;
    //  性别
    private int sex;
    //  学籍号
    private String studentCode;
    //  学级标识码
    private String gradeId;
    //  学级名称
    private String gradeName;
    //  班级标识码
    private String classId;
    //  班级名称
    private String className;
    //  人脸地址
    private String faceImgUrl;
    //  运动结果(带单位)
    private String sportResult;
    //  原始结果
    private String originalSportResult;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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

    public String getFaceImgUrl() {
        return faceImgUrl;
    }

    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public String getOriginalSportResult() {
        return originalSportResult;
    }

    public void setOriginalSportResult(String originalSportResult) {
        this.originalSportResult = originalSportResult;
    }
}
