package com.adl.service.entity;

import java.io.Serializable;

/**
 * Describe   : 运动记录详情
 * Author     : zhoumiqi
 * Date       : 2025/2/14
 */
public class SportDetailEntity implements Serializable {

    /**
     * 账号Id
     */
    private String accountId;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 日期
     */
    private String date;
    /**
     * 头像地址
     */
    private String faceImgUrl;
    /**
     * 配速
     */
    private String speed;
    /**
     * 运动结果
     */
    private String sportResult;
    /**
     * 运动时间
     */
    private long sportTime;
    /**
     * 班级Id
     */
    private String classId;
    /**
     * 年级Id
     */
    private String gradeId;
    /**
     * 原始运动结果
     */
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFaceImgUrl() {
        return faceImgUrl;
    }

    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public long getSportTime() {
        return sportTime;
    }

    public void setSportTime(long sportTime) {
        this.sportTime = sportTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getOriginalSportResult() {
        return originalSportResult;
    }

    public void setOriginalSportResult(String originalSportResult) {
        this.originalSportResult = originalSportResult;
    }
}
