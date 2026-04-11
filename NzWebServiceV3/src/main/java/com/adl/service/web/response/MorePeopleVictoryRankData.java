package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class MorePeopleVictoryRankData {

    /**
     * 用户标识码
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 用户姓名
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 用户头像
     */
    @SerializedName("faceUrl")
    private String faceUrl;

    /**
     * 胜利次数
     */
    @SerializedName("victoryNumber")
    private Integer victoryNumber;

    /**
     * 胜率
     */
    @SerializedName("victoryRate")
    private Double victoryRate;

    /**
     * 性别
     */
    @SerializedName("sex")
    private Integer sex;
}
