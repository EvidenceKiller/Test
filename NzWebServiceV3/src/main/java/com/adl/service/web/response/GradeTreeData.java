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
public final class GradeTreeData {
    /**
     * 年级ID
     */
    @SerializedName("id")
    private String id;

    /**
     * 入学年份 入学年份 入学时间（2019）
     */
    @SerializedName("enterYear")
    private Integer enterYear;

    /**
     * 毕业年份 毕业年份 毕业时间（2020）
     */
    @SerializedName("graduationYear")
    private Integer graduationYear;

    /**
     * 年级编码 org_code_enter_year
     */
    @SerializedName("gradeCode")
    private String gradeCode;

    /**
     * 年级名称
     */
    @SerializedName("gradeName")
    private String gradeName;

    /**
     * 部门编码 多个用-隔开
     */
    @SerializedName("departmentCodes")
    private String departmentCodes;

    /**
     * 是否毕业 参考字典编码：adl_tis_graduation_flag（1 毕业 0 未毕业）
     */
    @SerializedName("graduationFlag")
    private Integer graduationFlag;

    @SerializedName("classList")
    private List<GradeClassData> classList;

    /**
     * 年级序号（根据当前学段）
     */
    @SerializedName("gradeNumByStage")
    private Integer gradeNumByStage;

    /**
     * 年级序号（总年级编号）
     */
    @SerializedName("gradeNum")
    private Integer gradeNum;

    /**
     * 学段代码
     */
    @SerializedName("secCode")
    private String secCode;

    /**
     * 学段序号
     */
    @SerializedName("stageType")
    private String stageType;

    /**
     * 年级中文名称
     */
    @SerializedName("gradeChineseName")
    private String gradeChineseName;

    /**
     * 学段年级中文名称
     */
    @SerializedName("gradeStageChineseName")
    private String gradeStageChineseName;
}
