package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.adl.service.db.converter.ListPlanSportConverter;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 组织测试
 */
@Entity(tableName = "_sport_plan")
@TypeConverters(value = {ListPlanSportConverter.class})
public class SportPlanEntity implements Serializable {

    // 计划名称
    @ColumnInfo(name = "_plan_name")
    private String planName;

    // 计划标识码
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_plan_id")
    private String planId;

    // 计划类型, 体测：TC、中考：ZK
    @ColumnInfo(name = "_standard_type")
    private String standardType;

    // 计划开始时间
    @ColumnInfo(name = "_start_time")
    private String startTime;

    // 计划结束时间
    @ColumnInfo(name = "_end_time")
    private String endTime;

    // 计划创建时间
    @ColumnInfo(name = "_create_time")
    private String createTime;

    // 测试标准名称
    @ColumnInfo(name = "_standard_name")
    private String standardName;

    // 测试标准标识码
    @ColumnInfo(name = "_standard_id")
    private String standardId;

    // 学生数量
    @ColumnInfo(name = "_student_num")
    private int studentNum;

    @ColumnInfo(name = "_status")
    private String status;

    // 测试项目
    @ColumnInfo(name = "_sport_projects")
    private List<SportPlanProjectEntity> sportProjects;

    public SportPlanEntity() {
        this.planId = "";
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @NotNull
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(@NotNull String planId) {
        this.planId = planId;
    }

    public String getStandardType() {
        return standardType;
    }

    public void setStandardType(String standardType) {
        this.standardType = standardType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public List<SportPlanProjectEntity> getSportProjects() {
        return sportProjects;
    }

    public void setSportProjects(List<SportPlanProjectEntity> sportProjects) {
        this.sportProjects = sportProjects;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
