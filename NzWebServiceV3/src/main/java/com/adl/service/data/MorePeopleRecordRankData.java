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
public final class MorePeopleRecordRankData {
    /**
     * 用户ID
     */
    @SerializedName("uid")
    private String uid;

    /**
     * 多人对战最新战况
     */
    @SerializedName("morePeopleRecordRank")
    private List<MorePeopleRecordRankInfoData> morePeopleRecordRank;
}
