package com.adl.service.db.entity;

import com.adl.service.db.converter.ListSportMeetGroupDetailEntityConverter;
import com.adl.service.data.SportMeetGroupTeamData;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "_sport_meet_group_team")
@TypeConverters(value = {ListSportMeetGroupDetailEntityConverter.class})
public class SportMeetGroupTeamEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 应用编码【手动添加】
    @ColumnInfo(name = "_app_code")
    private String appCode;

    //  运动会id【手动添加】
    @ColumnInfo(name = "_meet_id")
    private String meetId;

    //  分组code
    @ColumnInfo(name = "_team_unit_code")
    private String teamUnitCode;

    //  分组名称
    @ColumnInfo(name = "_team_unit_name")
    private String teamUnitName;

    //  项目名称
    @ColumnInfo(name = "_sport_sku_name")
    private String sportSkuName;

    //  项目skuId
    @ColumnInfo(name = "_sport_sku_id")
    private String sportSkuId;

    //  运动会项目skuId
    @ColumnInfo(name = "_meet_sport_skuId")
    private String meetSportSkuId;

    //  组数
    @ColumnInfo(name = "_team_num")
    private int teamNum;

    //  人数
    @ColumnInfo(name = "_number")
    private int number;

    //  比赛状态 0未完赛 1已完赛
    @ColumnInfo(name = "_competition_status")
    private int competitionStatus;

    //  tis_competition_meet_type  1集体 2个人
    @ColumnInfo(name = "_competition_type")
    private int competitionType;

    //  轮次
    @ColumnInfo(name = "_competition_round")
    private int competitionRound;

    //  人员详情
    @ColumnInfo(name = "_details")
    private List<SportMeetGroupDetailEntity> details;

    public static SportMeetGroupTeamEntity convertToEntity(SportMeetGroupTeamData data, String appCode) {
        if (data == null) {
            return null;
        }
        SportMeetGroupTeamEntity entity = new SportMeetGroupTeamEntity();
        entity.setAppCode(appCode);
        entity.setMeetId(data.getMeetId());
        entity.setTeamUnitCode(data.getTeamUnitCode());
        entity.setTeamUnitName(data.getTeamUnitName());
        entity.setSportSkuName(data.getSportSkuName());
        entity.setSportSkuId(data.getSportSkuId());
        entity.setMeetSportSkuId(data.getMeetSportSkuId());
        entity.setTeamNum(data.getTeamNum());
        entity.setNumber(data.getNumber());
        entity.setCompetitionStatus(data.getCompetitionStatus());
        entity.setCompetitionType(data.getCompetitionType());
        entity.setCompetitionRound(data.getCompetitionRound());
        
        // 转换详情列表
        if (data.getDetails() != null && !data.getDetails().isEmpty()) {
            entity.setDetails(data.getDetails().stream()
                    .map(detailData -> SportMeetGroupDetailEntity.convertToEntity(detailData, appCode))
                    .collect(Collectors.toList()));
        }
        
        return entity;
    }

    public static SportMeetGroupTeamData convertToData(SportMeetGroupTeamEntity entity) {
        if (entity == null) {
            return null;
        }
        SportMeetGroupTeamData data = new SportMeetGroupTeamData();
        data.setMeetId(entity.getMeetId());
        data.setTeamUnitCode(entity.getTeamUnitCode());
        data.setTeamUnitName(entity.getTeamUnitName());
        data.setSportSkuName(entity.getSportSkuName());
        data.setSportSkuId(entity.getSportSkuId());
        data.setMeetSportSkuId(entity.getMeetSportSkuId());
        data.setTeamNum(entity.getTeamNum());
        data.setNumber(entity.getNumber());
        data.setCompetitionStatus(entity.getCompetitionStatus());
        data.setCompetitionType(entity.getCompetitionType());
        data.setCompetitionRound(entity.getCompetitionRound());
        if (entity.getDetails() != null) {
            data.setDetails(entity.getDetails().stream()
                    .map(SportMeetGroupDetailEntity::convertToData)
                    .collect(Collectors.toList()));
        }
        return data;
    }
}
