package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class PlanStudentData {
    /**
     * 账号标识码
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 准考证号
     */
    @SerializedName("admissionNum")
    private String admissionNum;

    /**
     * 班级标识码
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 年级标识码
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 年级名称
     */
    @SerializedName("gradeName")
    private String gradeName;

    /**
     * 计划id
     */
    @SerializedName("planId")
    private String planId;

    /**
     * 报名项目
     */
    @SerializedName("projects")
    private List<PlanSportData> projects;

    /**
     * 学生性别
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 学籍号
     */
    @SerializedName("studentCode")
    private String studentCode;

    /**
     * 学生姓名
     */
    @SerializedName("studentName")
    private String studentName;

    /**
     * 班级序号
     */
    @SerializedName("classNum")
    private Integer classNum;

    /**
     * 年级序号
     */
    @SerializedName("gradeNum")
    private Integer gradeNum;

    /**
     * 阶段ID
     */
    @SerializedName("stageId")
    private String stageId;

    /**
     * 学号
     */
    @SerializedName("studentNum")
    private String studentNum;

    /**
     * 账号名称拼音
     */
    @SerializedName("accountNamePinYin")
    private String accountNamePinYin;

    /**
     * 学院ID
     */
    @SerializedName("collegeId")
    private String collegeId;

    /**
     * 学院名称
     */
    @SerializedName("collegeName")
    private String collegeName;

    /**
     * 系部ID
     */
    @SerializedName("facultyId")
    private String facultyId;

    /**
     * 系部名称
     */
    @SerializedName("facultyName")
    private String facultyName;

    /**
     * 首字母拼音
     */
    @SerializedName("firstLetterPinYin")
    private String firstLetterPinYin;

    /**
     * 专业ID
     */
    @SerializedName("majorId")
    private String majorId;
}
