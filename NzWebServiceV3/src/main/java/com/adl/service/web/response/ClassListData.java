package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class ClassListData {

    /**
     * 班级ID
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 年级ID
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 班级编号
     */
    @SerializedName("classNum")
    private String classNum;

    /**
     * 班级全名
     */
    @SerializedName("classFullName")
    private String classFullName;

    /**
     * 学年学期代码
     */
    @SerializedName("acayearSemCode")
    private String acayearSemCode;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 年级名称
     */
    @SerializedName("gradeName")
    private String gradeName;

    /**
     * 学段编码
     */
    @SerializedName("secCode")
    private String secCode;

    /**
     * 专业ID
     */
    @SerializedName("majorId")
    private String majorId;

    /**
     * 专业代码
     */
    @SerializedName("majorCode")
    private String majorCode;

    /**
     * 专业名称
     */
    @SerializedName("majorName")
    private String majorName;

    /**
     * 院系ID
     */
    @SerializedName("facultyId")
    private String facultyId;

    /**
     * 院系代码
     */
    @SerializedName("facultyCode")
    private String facultyCode;

    /**
     * 院系名称
     */
    @SerializedName("facultyName")
    private String facultyName;

    /**
     * 学院ID
     */
    @SerializedName("collegeId")
    private String collegeId;

    /**
     * 学院代码
     */
    @SerializedName("collegeCode")
    private String collegeCode;

    /**
     * 学院名称
     */
    @SerializedName("collegeName")
    private String collegeName;

    /**
     * 年级中文名称
     */
    @SerializedName("gradeChineseName")
    private String gradeChineseName;
}
