package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;


/**
 * Name : SportDetailListData
 * Author : zhouxiangnan
 * Date : 2026/4/7
 * Describe : 定义接口响应数据结构
 */
@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class SunshineRunningSportDetailData {
    /**
     * 账号Id
     */
    @SerializedName("accountId")
    private String accountId;
    /**
     * 账号名称
     */
    @SerializedName("accountName")
    private String accountName;
    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;
    /**
     * 日期
     */
    @SerializedName("date")
    private String date;
    /**
     * 头像地址
     */
    @SerializedName("faceImgUrl")
    private String faceImgUrl;
    /**
     * 配速
     */
    @SerializedName("speed")
    private String speed;
    /**
     * 运动结果
     */
    @SerializedName("sportResult")
    private String sportResult;
    /**
     * 运动时间
     */
    @SerializedName("sportTime")
    private Long sportTime;
    /**
     * 运动开始时间
     */
    @SerializedName("sportStartTime")
    private Long sportStartTime;
    /**
     * 班级Id
     */
    @SerializedName("classId")
    private String classId;
    /**
     * 年级Id
     */
    @SerializedName("gradeId")
    private String gradeId;
    /**
     * 原始运动结果
     */
    @SerializedName("originalSportResult")
    private String originalSportResult;
}