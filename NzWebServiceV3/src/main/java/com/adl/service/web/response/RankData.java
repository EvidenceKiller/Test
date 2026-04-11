package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class RankData {
    /**
     * 用户标识码
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 学生姓名
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 学生头像
     */
    @SerializedName("accountAvatar")
    private String accountAvatar;

    /**
     * 学生性别 1 男 2 女
     */
    @SerializedName("sex")
    private String sex;

    /**
     * 成绩
     */
    @SerializedName("score")
    private Integer score;

    /**
     * 运动结果
     */
    @SerializedName("sportResult")
    private String sportResult;

    /**
     * 学校名
     */
    @SerializedName("orgName")
    private String orgName;

    /**
     * 班级名（年级班级一起返回）
     */
    @SerializedName("className")
    private String className;

    /**
     * 排名
     */
    @SerializedName("rankNum")
    private Integer rankNum;

    /**
     * 运动结束时间
     */
    @SerializedName("sportEndTime")
    private Long sportEndTime;

    /**
     * 运动得分
     */
    @SerializedName("sportScore")
    private Double sportScore;

    /**
     * 精度类型
     */
    @SerializedName("accuracyType")
    private Integer accuracyType;

    /**
     * 精度方式
     */
    @SerializedName("accuracyMethod")
    private Integer accuracyMethod;

    /**
     * 运动单位代码
     */
    @SerializedName("sportUnitCode")
    private String sportUnitCode;
}
