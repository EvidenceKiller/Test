package com.adl.service.db.entity;

import com.adl.service.data.SportMeetSkuData;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class SportMeetSkuEntity implements Serializable {
    //  运动会运动sku标识码
    private String id;
    //  运动会标识码
    private String meetId;
    //  运动sku标识码
    private String sportSkuId;
    //  运动sku标识码
    private String sportSkuName;
    //  类型名称
    private String sportTypeName;
    //  精度方式 详见码表
    private int accuracyMethod;
    //  精度类型 详见码表
    private int accuracyType;
    //  报名人数
    private int applyUserCount;
    //  比赛方式
    private int competitionType;
    //  封面地址
    private String coverUrl;
    //  结束类型 参考字典编码：adl_bms_sport_sku_end_type
    private int endType;
    //  结束方式 参考字典编码：adl_sport_end_unit
    private int endValue;
    //  结束方式为其他时的备注
    private String endRemark;
    //  测量方式 参考字典编码：adl_bms_sport_sku_measure_mode
    private String measureMode;
    //  测量单位代码 参考字典编码：adl_sport_sport_unit
    private String measureUnitCode;
    //  排序 低优策略
    private int meetSkuSort;
    //  项目设置id
    private String settingId;
    //  项目高低优 参考字典编码：adl_bms_sport_priority
    private int sportPriority;
    //  测试人数
    private int testCount;

    private int competitionNum;

    private List<String> appCodes;

    public static SportMeetSkuEntity convertToEntity(SportMeetSkuData data) {
        if (data == null) {
            return null;
        }
        SportMeetSkuEntity entity = new SportMeetSkuEntity();
        entity.setId(data.getId());
        entity.setMeetId(data.getMeetId());
        entity.setSportSkuId(data.getSportSkuId());
        entity.setSportSkuName(data.getSportSkuName());
        entity.setSportTypeName(data.getSportTypeName());
        entity.setAccuracyMethod(data.getAccuracyMethod());
        entity.setAccuracyType(data.getAccuracyType());
        entity.setApplyUserCount(data.getApplyUserCount());
        entity.setCompetitionType(data.getCompetitionType());
        entity.setCoverUrl(data.getCoverUrl());
        entity.setEndType(data.getEndType());
        entity.setEndValue(data.getEndValue());
        entity.setEndRemark(data.getEndRemark());
        entity.setMeasureMode(data.getMeasureMode());
        entity.setMeasureUnitCode(data.getMeasureUnitCode());
        entity.setMeetSkuSort(data.getMeetSkuSort());
        entity.setSettingId(data.getSettingId());
        entity.setSportPriority(data.getSportPriority());
        entity.setTestCount(data.getTestCount());
        entity.setCompetitionNum(data.getCompetitionNum());
        entity.setAppCodes(data.getAppCodes());
        return entity;
    }

    public static SportMeetSkuData convertToData(SportMeetSkuEntity entity) {
        if (entity == null) {
            return null;
        }
        SportMeetSkuData data = new SportMeetSkuData();
        data.setId(entity.getId());
        data.setMeetId(entity.getMeetId());
        data.setSportSkuId(entity.getSportSkuId());
        data.setSportSkuName(entity.getSportSkuName());
        data.setSportTypeName(entity.getSportTypeName());
        data.setAccuracyMethod(entity.getAccuracyMethod());
        data.setAccuracyType(entity.getAccuracyType());
        data.setApplyUserCount(entity.getApplyUserCount());
        data.setCompetitionType(entity.getCompetitionType());
        data.setCoverUrl(entity.getCoverUrl());
        data.setEndType(entity.getEndType());
        data.setEndValue(entity.getEndValue());
        data.setEndRemark(entity.getEndRemark());
        data.setMeasureMode(entity.getMeasureMode());
        data.setMeasureUnitCode(entity.getMeasureUnitCode());
        data.setMeetSkuSort(entity.getMeetSkuSort());
        data.setSettingId(entity.getSettingId());
        data.setSportPriority(entity.getSportPriority());
        data.setTestCount(entity.getTestCount());
        data.setCompetitionNum(entity.getCompetitionNum());
        data.setAppCodes(entity.getAppCodes());
        return data;
    }
}
