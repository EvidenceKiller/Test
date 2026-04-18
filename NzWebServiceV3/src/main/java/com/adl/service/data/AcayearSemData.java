package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class AcayearSemData {
    /**
     * 学期code
     */
    @SerializedName("acayearSemCode")
    private String acayearSemCode;

    /**
     * 学期name
     */
    @SerializedName("acayearSemName")
    private String acayearSemName;

    /**
     * 学年编码 2022-2023
     */
    @SerializedName("acayearCode")
    private String acayearCode;

    /**
     * 学年开始时间
     */
    @SerializedName("startDate")
    private String startDate;

    /**
     * 学年截止时间
     */
    @SerializedName("endDate")
    private String endDate;

    /**
     * 是否当前学期 1：是 0: 不是
     */
    @SerializedName("isNowAcayearSem")
    private Integer isNowAcayearSem;
}
