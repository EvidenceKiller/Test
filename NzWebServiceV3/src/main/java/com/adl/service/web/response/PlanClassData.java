package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class PlanClassData {

    /**
     * 班级id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 年级名称
     */
    @SerializedName("gradeName")
    private String gradeName;

    /**
     * 年级id
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 学院id
     */
    @SerializedName("collegeId")
    private String collegeId;

    /**
     * 学院名称
     */
    @SerializedName("collegeName")
    private String collegeName;

    /**
     * 专业id
     */
    @SerializedName("majorId")
    private String majorId;

    /**
     * 专业名称
     */
    @SerializedName("majorName")
    private String majorName;

    /**
     * 系部id
     */
    @SerializedName("facultyId")
    private String facultyId;

    /**
     * 系部名称
     */
    @SerializedName("facultyName")
    private String facultyName;

    /**
     * 学段编号
     */
    @SerializedName("periodNum")
    private String periodNum;

    /**
     * 年级编号
     */
    @SerializedName("gradeNum")
    private String gradeNum;
}
