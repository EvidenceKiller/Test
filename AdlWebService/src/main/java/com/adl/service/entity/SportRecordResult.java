package com.adl.service.entity;

import androidx.room.ColumnInfo;

public class SportRecordResult {
    //  用户账号id(非必须)
    @ColumnInfo(name = "_account_id")
    private String accountId;

    //  运动sku编码，sku配置表
    @ColumnInfo(name = "_sport_sku_id")
    private String sportSkuId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }
}
