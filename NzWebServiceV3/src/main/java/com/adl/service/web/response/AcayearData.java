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
public final class AcayearData {

    /**
     * 学年
     */
    @SerializedName("year")
    private String year;

    /**
     * 学期阶段list
     */
    @SerializedName("acayearSemList")
    private List<AcayearSemData> acayearSemList;

    /**
     * 是否当前学年 1：是 0 不是
     */
    @SerializedName("isNowAcayear")
    private Integer isNowAcayear;
}
