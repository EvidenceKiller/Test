package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseGroupAccountInfo {
    /**
     * 账户ID
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 账户名称
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 账户名称首字母拼音
     */
    @SerializedName("accountNameFirstLetterPinYin")
    private String accountNameFirstLetterPinYin;

    /**
     * 账户NamePinYin
     * 账户姓名拼音
     */
    @SerializedName("accountNamePinYin")
    private String accountNamePinYin;

    /**
     * 序号
     */
    @SerializedName("accountSort")
    private Integer accountSort;

    /**
     * 班级ID
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 班次编号
     */
    @SerializedName("classCode")
    private String classCode;

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
     * 标记检查
     */
    @SerializedName("flagChecked")
    private String flagChecked;

    /**
     * 年级ID
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 年级名称
     */
    @SerializedName("gradeName")
    private String gradeName;

    /**
     * 年级阶段中文名称
     */
    @SerializedName("gradeStageChineseName")
    private String gradeStageChineseName;

    /**
     * 图片URL
     */
    @SerializedName("imgUrl")
    private String imgUrl;

    /**
     * 专业代码
     */
    @SerializedName("majorCode")
    private String majorCode;

    /**
     * 专业ID
     */
    @SerializedName("majorId")
    private String majorId;

    /**
     * 专业名称
     */
    @SerializedName("majorName")
    private String majorName;

    /**
     * 原始结果
     */
    @SerializedName("originalSportResult")
    private String originalSportResult;

    /**
     * 号码牌
     */
    @SerializedName("raceNumber")
    private String raceNumber;

    /**
     * 成绩
     */
    @SerializedName("result")
    private String result;

    /**
     * 跑道序号
     */
    @SerializedName("runwayNumber")
    private String runwayNumber;

    /**
     * 性别
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 学生编码
     */
    @SerializedName("studentCode")
    private String studentCode;
}
