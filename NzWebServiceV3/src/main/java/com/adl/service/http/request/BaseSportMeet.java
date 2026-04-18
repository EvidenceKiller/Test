package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseSportMeet {
    /**
     * 运动会id
     */
    @SerializedName("meetId")
    private String meetId;

    /**
     * 班级Id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * tis_competition_meet_type  1团体 2个人
     */
    @SerializedName("meetGroupType")
    private Integer meetGroupType;

    /**
     * 运动会运动员编码
     */
    @SerializedName("meetNumberCode")
    private String meetNumberCode;

    /**
     * 比赛轮次
     */
    @SerializedName("meetRound")
    private String meetRound;

    /**
     * 运动会跑道号
     */
    @SerializedName("meetRunwayCode")
    private String meetRunwayCode;

    /**
     * 运动会组别
     */
    @SerializedName("meetTeamNo")
    private String meetTeamNo;
    /**
     * 分组单位  学级_班级_性别
     */
    @SerializedName("teamUnitCode")
    private String teamUnitCode;

    /**
     * 运动结果同步标识
     */
    @SerializedName("resultSyncFlag")
    private Boolean resultSyncFlag;

    /**
     * 运动会运动sku关系标识码
     */
    @SerializedName("meetSportSkuId")
    private String meetSportSkuId;
    /**
     * 序号
     */
    @SerializedName("serialNumber")
    private String serialNumber;
}
