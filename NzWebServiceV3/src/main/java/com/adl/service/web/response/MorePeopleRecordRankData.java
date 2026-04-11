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
