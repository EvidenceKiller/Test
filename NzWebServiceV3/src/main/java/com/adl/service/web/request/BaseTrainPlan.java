package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseTrainPlan {
    /**
     * 特训计划id
     */
    @SerializedName("trainPlanId")
    private String trainPlanId;

    /**
     * 特训计划名称
     */
    @SerializedName("trainPlanName")
    private String trainPlanName;

    /**
     * 特训计划运动id
     */
    @SerializedName("trainPlanSportId")
    private String trainPlanSportId;

    /**
     * 特训计划运动名称
     */
    @SerializedName("trainPlanSportName")
    private String trainPlanSportName;
}
