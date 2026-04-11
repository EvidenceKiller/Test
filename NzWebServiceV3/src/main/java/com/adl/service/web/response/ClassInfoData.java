package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class ClassInfoData {

    /**
     * 班级id
     */
    @SerializedName("id")
    private String id;

    /**
     * 班级代码 1 2 3 4 5
     */
    @SerializedName("classNum")
    private Integer classNum;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 班级全称 班级全称 小学一年级一班
     */
    @SerializedName("classFullName")
    private String classFullName;

    /**
     * 专业代码 （高校）
     */
    @SerializedName("departmentCode")
    private String departmentCode;

    /**
     * 班级状态 1. 进行中 2. 未开学 3. 已毕业
     */
    @SerializedName("status")
    private Integer status;

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
}
