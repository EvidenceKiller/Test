package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
