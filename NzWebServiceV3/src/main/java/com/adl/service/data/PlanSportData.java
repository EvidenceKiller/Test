package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class PlanSportData {
    /**
     * 项目性别限制
     */
    @SerializedName("sex")
    private Integer sex;

    /**
     * 运动skuId
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 运动名称
     */
    @SerializedName("sportSkuName")
    private String sportSkuName;
}
