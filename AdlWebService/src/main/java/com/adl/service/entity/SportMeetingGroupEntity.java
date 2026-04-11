package com.adl.service.entity;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 运动会编排
 */
public class SportMeetingGroupEntity implements Serializable {

    private String groupId;

    private String studentName;

    private String studentId;

    private String studentCode;

    private String sportSkuName;

    private String sportSkuId;

    private String sex;

    private String collegeName;

    private String facultyName;

    // 专业
    private String majorName;

    // 年级
    private String gradeName;

    // 班级
    private String className;

    // 组别
    private String groupNum;

    // 跑道
    private String runwayCode;

    // 运动员编号
    private String numberCode;

    // 是否导入数据
    private String importFlag;

    private String autoSettingFlag;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getSportSkuName() {
        return sportSkuName;
    }

    public void setSportSkuName(String sportSkuName) {
        this.sportSkuName = sportSkuName;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getRunwayCode() {
        return runwayCode;
    }

    public void setRunwayCode(String runwayCode) {
        this.runwayCode = runwayCode;
    }

    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode;
    }

    public String getImportFlag() {
        return importFlag;
    }

    public void setImportFlag(String importFlag) {
        this.importFlag = importFlag;
    }

    public String getAutoSettingFlag() {
        return autoSettingFlag;
    }

    public void setAutoSettingFlag(String autoSettingFlag) {
        this.autoSettingFlag = autoSettingFlag;
    }
}
