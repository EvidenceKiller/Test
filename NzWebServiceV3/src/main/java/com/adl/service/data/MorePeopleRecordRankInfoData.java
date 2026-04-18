package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class MorePeopleRecordRankInfoData {
    //  账号id
    @SerializedName("accountId")
    private String accountId;
    //  账号名
    @SerializedName("accountName")
    private String accountName;
    //  头像
    @SerializedName("accountFacePath")
    private String accountFacePath;
    //  成绩
    @SerializedName("achievement")
    private String achievement;
    //  个数
    @SerializedName("count")
    private String count;
    //  时间
    @SerializedName("time")
    private String time;
    //  多人排行
    @SerializedName("multiPersonRank")
    private String multiPersonRank;
}
