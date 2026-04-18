package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class BaseCompetitionAthletics {
    /**
     * 赛事id
     */
    @SerializedName("competitionId")
    private String competitionId;

    /**
     * 赛事名称
     */
    @SerializedName("competitionName")
    private String competitionName;

    /**
     * 赛事运动id
     */
    @SerializedName("competitionAthSportId")
    private String competitionAthSportId;

    /**
     * 赛事运动名称
     */
    @SerializedName("competitionAthSportName")
    private String competitionAthSportName;

    /**
     * 打卡状态
     */
    @SerializedName("competitionCheckStatus")
    private Boolean competitionCheckStatus;

    /**
     * 完成状态
     */
    @SerializedName("competitionTaskStatus")
    private Boolean competitionTaskStatus;
}
