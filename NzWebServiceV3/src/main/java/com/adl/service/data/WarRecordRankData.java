package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class WarRecordRankData {

    /**
     * 用户ID
     */
    @SerializedName("uid")
    private String uid;

    /**
     * 胜者用户ID
     */
    @SerializedName("winnerUid")
    private String winnerUid;

    /**
     * 胜者姓名
     */
    @SerializedName("winnerName")
    private String winnerName;

    /**
     * 胜者头像路径
     */
    @SerializedName("winnerHeadPath")
    private String winnerHeadPath;

    /**
     * 胜者成绩
     */
    @SerializedName("winnerAchievement")
    private Integer winnerAchievement;

    /**
     * 胜者个数
     */
    @SerializedName("winnerCount")
    private Integer winnerCount;

    /**
     * 胜者时间
     */
    @SerializedName("winnerTime")
    private Integer winnerTime;

    /**
     * 败者用户ID
     */
    @SerializedName("loserUid")
    private String loserUid;

    /**
     * 败者姓名
     */
    @SerializedName("loserName")
    private String loserName;

    /**
     * 败者头像路径
     */
    @SerializedName("loserHeadPath")
    private String loserHeadPath;

    /**
     * 败者成绩
     */
    @SerializedName("loserAchievement")
    private Integer loserAchievement;

    /**
     * 败者个数
     */
    @SerializedName("loserCount")
    private Integer loserCount;

    /**
     * 败者时间
     */
    @SerializedName("loserTime")
    private Integer loserTime;
}
