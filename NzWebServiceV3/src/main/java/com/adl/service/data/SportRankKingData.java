package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class SportRankKingData {

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
    @SerializedName("userAvatar")
    private String userAvatar;

    /**
     * 学生性别 1 男 2 女
     */
    @SerializedName("sex")
    private String sex;

    /**
     * 运动SKU名称
     */
    @SerializedName("sportSkuName")
    private String sportSkuName;

    /**
     * 运动SKU ID
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 运动得分
     */
    @SerializedName("sportScore")
    private Double sportScore;

    /**
     * 运动结果
     */
    @SerializedName("sportResult")
    private String sportResult;

    /**
     * 原始运动结果
     */
    @SerializedName("originalSportResult")
    private String originalSportResult;

    /**
     * 运动时间
     */
    @SerializedName("sportTime")
    private Integer sportTime;

    /**
     * 运动计数
     */
    @SerializedName("sportCount")
    private Integer sportCount;

    /**
     * 运动打卡日期
     */
    @SerializedName("sportDate")
    private String sportDate;

    /**
     * 记录ID
     */
    @SerializedName("recordId")
    private String recordId;

    /**
     * 运动类型代码
     */
    @SerializedName("sportItemCode")
    private String sportItemCode;

    /**
     * 霸榜项目数
     */
    @SerializedName("count")
    private Integer count;
}
