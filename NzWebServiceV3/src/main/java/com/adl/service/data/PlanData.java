package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class PlanData implements Serializable {
    /**
     * 创建时间
     */
    @SerializedName("createTime")
    private String createTime;

    /**
     * 创建者名称
     */
    @SerializedName("creatorName")
    private String creatorName;

    /**
     * 结束时间
     */
    @SerializedName("endTime")
    private String endTime;

    /**
     * 计划id
     */
    @NotNull
    @PrimaryKey
    @SerializedName("planId")
    private String planId;

    /**
     * 计划名称
     */
    @SerializedName("planName")
    private String planName;

    /**
     * 运动项目列表
     */
    @SerializedName("sportProjects")
    private List<SportProjectData> sportProjects;

    /**
     * 标准id
     */
    @SerializedName("standardId")
    private String standardId;

    /**
     * 标准名称
     */
    @SerializedName("standardName")
    private String standardName;

    /**
     * 标准类型
     */
    @SerializedName("standardType")
    private String standardType;

    /**
     * 开始时间
     */
    @SerializedName("startTime")
    private String startTime;

    /**
     * 状态
     */
    @SerializedName("status")
    private String status;

    /**
     * 学生数量
     */
    @SerializedName("studentNum")
    private Integer studentNum;

    /**
     * 安排数量
     */
    @SerializedName("arrangeNum")
    private Integer arrangeNum;

    /**
     * 完成数量
     */
    @SerializedName("finishNum")
    private Integer finishNum;

    /**
     * 自测标识
     */
    @SerializedName("selfTest")
    private Integer selfTest;
}
