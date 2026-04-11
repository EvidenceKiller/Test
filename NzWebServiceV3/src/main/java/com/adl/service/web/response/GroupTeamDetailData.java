package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class GroupTeamDetailData {

    /**
     * 分组code
     */
    @SerializedName("teamUnitCode")
    private String teamUnitCode;

    /**
     * 分组名称
     */
    @SerializedName("teamUnitName")
    private String teamUnitName;

    /**
     * 组号
     */
    @SerializedName("groupNo")
    private Integer groupNo;

    /**
     * 识别类型 参考字典编码：adl_tis_competition_meet_identify_type（0跑道识别 1运动员编码 2序号识别）
     */
    @SerializedName("identifyType")
    private Integer identifyType;

    /**
     * 跑道号
     */
    @SerializedName("runwayCode")
    private String runwayCode;

    /**
     * 运动员编号
     */
    @SerializedName("numberCode")
    private String numberCode;

    /**
     * 序号
     */
    @SerializedName("serialCode")
    private String serialCode;

    /**
     * 运动员编码
     */
    @SerializedName("sportAccountNum")
    private String sportAccountNum;

    /**
     * 头像
     */
    @SerializedName("accountAvatar")
    private String accountAvatar;

    /**
     * 运动员姓名
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 运动员id
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 班级id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 性别
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 团体人数
     */
    @SerializedName("studentNum")
    private Integer studentNum;

    /**
     * 运动结果
     */
    @SerializedName("sportResult")
    private String sportResult;

    /**
     * 运动结果转换后
     */
    @SerializedName("sportResultConverted")
    private String sportResultConverted;

    /**
     * 运动结果id
     */
    @SerializedName("recordId")
    private String recordId;
}
