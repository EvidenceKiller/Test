package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class TrainPlanOneDayProjectData {

    /**
     * 训练计划 ID
     */
    @SerializedName("trainPlanId")
    private String trainPlanId;

    /**
     * 排序
     */
    @SerializedName("sort")
    private Integer sort;

    /**
     * 运动个数
     */
    @SerializedName("sportCount")
    private Integer sportCount;

    /**
     * 1 计时 2 计数 99 其他
     */
    @SerializedName("sportModel")
    private String sportModel;

    /**
     * 运动时间 (ms)
     */
    @SerializedName("sportTime")
    private Integer sportTime;

    /**
     * 运动成绩
     */
    @SerializedName("sportResult")
    private String sportResult;

    /**
     * 打卡完成，0 待完成 1 已完成
     */
    @SerializedName("checkStatus")
    private Boolean checkStatus;

    /**
     * sku 唯一标识
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * sku 名称
     */
    @SerializedName("sportSkuName")
    private String sportSkuName;

    /**
     * 训练计划运动 id
     */
    @SerializedName("trainPlanSportId")
    private String trainPlanSportId;
}
