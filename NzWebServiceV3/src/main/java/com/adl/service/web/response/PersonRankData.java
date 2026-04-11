package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class PersonRankData {

    /**
     * 排名
     */
    @SerializedName("rankNum")
    private Long rankNum;

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
}
