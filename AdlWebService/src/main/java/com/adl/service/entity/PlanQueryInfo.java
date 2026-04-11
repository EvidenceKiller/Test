package com.adl.service.entity;

import androidx.room.ColumnInfo;

/**
 * hhd
 * 2026/1/5
 */
public class PlanQueryInfo {
    @ColumnInfo(name = "_scene_sub_id")
    private String planId;
    @ColumnInfo(name = "_scene_sub_name")
    private String planName;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
}
