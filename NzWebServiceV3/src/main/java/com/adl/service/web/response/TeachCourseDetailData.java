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
public final class TeachCourseDetailData {
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
     * 头像图片URL
     */
    @SerializedName("faceImgUrl")
    private String faceImgUrl;

    /**
     * 运动分数
     */
    @SerializedName("sportScore")
    private Double sportScore;

    /**
     * 运动结果
     */
    @SerializedName("sportResult")
    private String sportResult;

    /**
     * 原始结果
     */
    @SerializedName("originalSportResult")
    private String originalSportResult;

    /**
     * 上一次运动结果
     */
    @SerializedName("lastSportResult")
    private String lastSportResult;

    /**
     * 上一次原始结果
     */
    @SerializedName("lastOriginalSportResult")
    private String lastOriginalSportResult;

    /**
     * 记录标识码
     */
    @SerializedName("recordId")
    private String recordId;

    /**
     * 课程次数
     */
    @SerializedName("courseTimes")
    private Integer courseTimes;

    /**
     * 运动单位
     */
    @SerializedName("sportUnitCode")
    private String sportUnitCode;

    /**
     * 运动时长
     */
    @SerializedName("sportTime")
    private Long sportTime;

    /**
     * 运动开始时间
     */
    @SerializedName("sportStartTime")
    private Long sportStartTime;

    /**
     * 运动结束时间
     */
    @SerializedName("sportEndTime")
    private Long sportEndTime;

    /**
     * 运动测试时间
     */
    @SerializedName("sportTestTime")
    private Long sportTestTime;

    /**
     * 账户性别
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 运动视频集合
     */
    @SerializedName("sportVideoUrls")
    private List<String> sportVideoUrls;

    /**
     * 运动指标集合
     */
    @SerializedName("list")
    private List<SportIndicatorsData> list;

    /**
     * 横向报告URL
     */
    @SerializedName("reportCrosswiseUrl")
    private String reportCrosswiseUrl;

    /**
     * 竖向报告URL
     */
    @SerializedName("reportVerticalUrl")
    private String reportVerticalUrl;
}
