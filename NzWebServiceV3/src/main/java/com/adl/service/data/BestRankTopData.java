package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class BestRankTopData {

    /**
     * 用户标识码
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 用户名
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 账户性别
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 学籍号
     */
    @SerializedName("studentCode")
    private String studentCode;

    /**
     * 学级标识码
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 学级名称
     */
    @SerializedName("gradeName")
    private String gradeName;

    /**
     * 班级标识码
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 人脸地址
     */
    @SerializedName("faceImgUrl")
    private String faceImgUrl;

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
     * 运动结束时间
     */
    @SerializedName("sportEndTime")
    private Long sportEndTime;
}
