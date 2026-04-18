package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public class SportMeetGroupDetailData implements Serializable {
    //  运动会id【手动添加】
    @SerializedName("meetId")
    private String meetId;

    //  运动会项目skuId【手动添加】
    @SerializedName("meetSportSkuId")
    private String meetSportSkuId;

    //  轮次【手动添加】
    @SerializedName("competitionRound")
    private int competitionRound;

    //  分组code
    @SerializedName("teamUnitCode")
    private String teamUnitCode;

    //  分组名称
    @SerializedName("teamUnitName")
    private String teamUnitName;

    //  组号
    @SerializedName("groupNo")
    private int groupNo;

    //  识别类型  参考字典编码：adl_tis_competition_meet_identify_type（0跑道识别 1运动员编码 2序号识别）
    @SerializedName("identifyType")
    private int identifyType;

    //  跑道号
    @SerializedName("runwayCode")
    private String runwayCode;

    //  运动员编号
    @SerializedName("numberCode")
    private String numberCode;

    //  序号
    @SerializedName("serialCode")
    private String serialCode;

    //  运动员编码
    @SerializedName("sportAccountNum")
    private String sportAccountNum;

    //  运动员姓名
    @SerializedName("accountName")
    private String accountName;

    //  运动员id
    @SerializedName("accountId")
    private String accountId;

    //  班级id
    @SerializedName("classId")
    private String classId;

    //  班级名称
    @SerializedName("className")
    private String className;

    //  头像
    @SerializedName("accountAvatar")
    private String accountAvatar;

    //  运动结果
    @SerializedName("sportResult")
    private String sportResult;
}
