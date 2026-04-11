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
public final class TrainPlanInfoData {
    /**
     * 机构ID
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 创建时间
     */
    @SerializedName("createTime")
    private Long createTime;

    /**
     * 机构名称
     */
    @SerializedName("orgName")
    private String orgName;

    /**
     * 结束时间
     */
    @SerializedName("endTime")
    private Long endTime;

    /**
     * 开始时间
     */
    @SerializedName("startTime")
    private Long startTime;

    /**
     * 标题
     */
    @SerializedName("title")
    private String title;

    /**
     * 唯一标识
     */
    @SerializedName("uid")
    private String uid;

    /**
     * 时间状态
     */
    @SerializedName("timeStatus")
    private String timeStatus;

    /**
     * 培训计划时间列表
     */
    @SerializedName("trainPlanTimeList")
    private List<Long> trainPlanTimeList;

    /**
     * 培训计划天数
     */
    @SerializedName("trainPlanDays")
    private Integer trainPlanDays;

    /**
     * 账号数量
     */
    @SerializedName("accountNumber")
    private Integer accountNumber;

    /**
     * 培训计划描述
     */
    @SerializedName("trainPlanDesc")
    private String trainPlanDesc;

    /**
     * 检查数据
     */
    @SerializedName("checkData")
    private Boolean checkData;
}
