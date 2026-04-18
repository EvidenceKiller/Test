package com.adl.service.db.entity;

import com.adl.service.data.PlanSportData;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class PlanSportEntity {
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

    public static PlanSportEntity convertToEntity(PlanSportData data) {
        if (data == null) {
            return null;
        }
        PlanSportEntity entity = new PlanSportEntity();
        entity.setSex(data.getSex());
        entity.setSportSkuId(data.getSportSkuId());
        entity.setSportSkuName(data.getSportSkuName());
        return entity;
    }

    public static PlanSportData convertToData(PlanSportEntity entity) {
        if (entity == null) {
            return null;
        }
        PlanSportData data = new PlanSportData();
        data.setSex(entity.getSex());
        data.setSportSkuId(entity.getSportSkuId());
        data.setSportSkuName(entity.getSportSkuName());
        return data;
    }
}
