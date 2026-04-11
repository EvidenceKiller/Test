package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 标准配置
 */
@Entity(tableName = "_sport_standard")
public class StandardConfigEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 本地新增字段, 1.组织测试，2.运动会, 3.其他
    @ColumnInfo(name = "_group_type")
    private String groupTyp;

    // 本地新增字段,id
    @ColumnInfo(name = "_group_ip")
    private String groupId;

    // 运动项目标识码
    @ColumnInfo(name = "_sport_project_code")
    private String sportProjectCode;

    // 学段编号
    @ColumnInfo(name = "_period_num")
    private String periodNum;

    // 年级编号
    @ColumnInfo(name = "_grade_num")
    private String gradeNum;

    // 运动成绩对应等级
    @ColumnInfo(name = "_level")
    private String level;

    // 运动成绩对应分数
    @ColumnInfo(name = "_score")
    private String score;

    // 运动成绩
    @ColumnInfo(name = "_sport_result")
    private String sportResult;

    // 标准对应性别
    @ColumnInfo(name = "_sex")
    private String sex;

    // 码标准标识
    @ColumnInfo(name = "_standard_id")
    private String standardId;

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public String getGroupTyp() {
        return groupTyp;
    }

    public void setGroupTyp(String groupTyp) {
        this.groupTyp = groupTyp;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSportProjectCode() {
        return sportProjectCode;
    }

    public void setSportProjectCode(String sportProjectCode) {
        this.sportProjectCode = sportProjectCode;
    }

    public String getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(String periodNum) {
        this.periodNum = periodNum;
    }

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }
}
