package com.adl.service.entity;

import java.io.Serializable;

public class TempGroupAccountInfo implements Serializable {
    private String accountId;
    private String accountName;
    private int accountSort;
    private String classId;
    private String className;
    private String gradeId;
    private String gradeName;
    private String originalSportResult;
    private String raceNumber;
    private String result;
    private String runwayNumber;
    private int sex;

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

    public Integer getAccountSort() {
        return accountSort;
    }

    public void setAccountSort(Integer accountSort) {
        this.accountSort = accountSort;
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

    public String getOriginalSportResult() {
        return originalSportResult;
    }

    public void setOriginalSportResult(String originalSportResult) {
        this.originalSportResult = originalSportResult;
    }

    public String getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(String raceNumber) {
        this.raceNumber = raceNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRunwayNumber() {
        return runwayNumber;
    }

    public void setRunwayNumber(String runwayNumber) {
        this.runwayNumber = runwayNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
} 