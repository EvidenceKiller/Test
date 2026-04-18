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
public class SportMeetGroupTeamData {
    //  运动会id【手动添加】
    @SerializedName("meetId")
    private String meetId;

    //  分组code
    @SerializedName("teamUnitCode")
    private String teamUnitCode;

    //  分组名称
    @SerializedName("teamUnitName")
    private String teamUnitName;

    //  项目名称
    @SerializedName("sportSkuName")
    private String sportSkuName;

    //  项目skuId
    @SerializedName("sportSkuId")
    private String sportSkuId;

    //  运动会项目skuId
    @SerializedName("meetSportSkuId")
    private String meetSportSkuId;

    //  组数
    @SerializedName("teamNum")
    private int teamNum;

    //  人数
    @SerializedName("number")
    private int number;

    //  比赛状态 0未完赛 1已完赛
    @SerializedName("competitionStatus")
    private int competitionStatus;

    //  tis_competition_meet_type  1集体 2个人
    @SerializedName("competitionType")
    private int competitionType;

    //  轮次
    @SerializedName("competitionRound")
    private int competitionRound;

    //  人员详情
    @SerializedName("details")
    private List<SportMeetGroupDetailData> details;
}
