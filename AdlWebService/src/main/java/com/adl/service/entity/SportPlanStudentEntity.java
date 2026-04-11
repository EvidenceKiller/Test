package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 组织测试下的学生
 */
@Entity(tableName = "_sport_plan_student_1"
        , indices = {@Index(value = {"_plan_id"})})
public class SportPlanStudentEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 计划id
    @ColumnInfo(name = "_plan_id")
    private String planId;

    // 学生姓名
    @ColumnInfo(name = "_student_name")
    private String studentName;

    // 学籍号
    @ColumnInfo(name = "_student_code")
    private String studentCode;

    //移动学生学号
    @ColumnInfo(name = "_student_num")
    private String studentNum;

    // 学生性别
    @ColumnInfo(name = "_sex")
    private String sex;

    // 准考证号
    @ColumnInfo(name = "_admission_num")
    private String admissionNum;

    // 账号标识码
    @ColumnInfo(name = "_account_id")
    private String accountId;

    // 班级标识码
    @ColumnInfo(name = "_class_id")
    private String classId;

    // 班级名称
    @ColumnInfo(name = "_class_name")
    private String className;

    // 年级标识码
    @ColumnInfo(name = "_grade_id")
    private String gradeId;

    // 年级标识码
    @ColumnInfo(name = "_grade_name")
    private String gradeName;

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdmissionNum() {
        return admissionNum;
    }

    public void setAdmissionNum(String admissionNum) {
        this.admissionNum = admissionNum;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }
}
