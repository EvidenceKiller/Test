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
public final class CompetitionRankData {

    /**
     * 我的排名
     */
    @SerializedName("myRank")
    private RankData myRank;

    /**
     * 排行榜列表
     */
    @SerializedName("rankList")
    private List<RankData> rankList;
}
