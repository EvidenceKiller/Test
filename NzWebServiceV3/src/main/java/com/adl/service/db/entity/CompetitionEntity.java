package com.adl.service.db.entity;

import com.adl.service.data.CompetitionData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "_competition")
public final class CompetitionEntity {
    /**
     * 唯一标识符
     */
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    /**
     * 机构ID
     */
    @ColumnInfo(name = "_org_id")
    private String orgId;

    /**
     * 在线标题
     */
    @ColumnInfo(name = "_online_title")
    private String onlineTitle;

    /**
     * 在线副标题
     */
    @ColumnInfo(name = "_online_subtitle")
    private String onlineSubtitle;

    /**
     * 在线封面图片地址
     */
    @ColumnInfo(name = "_online_cover")
    private String onlineCover;

    /**
     * 在线类型
     */
    @ColumnInfo(name = "_online_type")
    private Integer onlineType;

    /**
     * 机构级别
     */
    @ColumnInfo(name = "_org_level")
    private Integer orgLevel;

    /**
     * 开始时间
     */
    @ColumnInfo(name = "_start_time")
    private Long startTime;

    /**
     * 结束时间
     */
    @ColumnInfo(name = "_end_time")
    private Long endTime;

    /**
     * 应用代码列表
     */
    @ColumnInfo(name = "_app_codes")
    private List<String> appCodes;

    /**
     * 创建者编码
     */
    @ColumnInfo(name = "_creator_num")
    private String creatorNum;

    /**
     * 创建时间
     */
    @ColumnInfo(name = "_create_time")
    private Long createTime;

    /**
     * 竞赛启用状态
     */
    @ColumnInfo(name = "_competition_enabled")
    private Integer competitionEnabled;

    /**
     * 编辑标志
     */
    @ColumnInfo(name = "_edit_flag")
    private Boolean editFlag;

    /**
     * 机构名称列表
     */
    @ColumnInfo(name = "_org_names")
    private List<String> orgNames;

    /**
     * 机构类型
     */
    @ColumnInfo(name = "_org_type")
    private Integer orgType;

    /**
     * 创建者机构名称
     */
    @ColumnInfo(name = "_creator_org_name")
    private String creatorOrgName;

    /**
     * 参与人数
     */
    @ColumnInfo(name = "_join_num")
    private Integer joinNum;

    public static CompetitionEntity convertToEntity(CompetitionData data) {
        if (data == null) {
            return null;
        }
        CompetitionEntity entity = new CompetitionEntity();
        entity.setId(data.getId());
        entity.setOrgId(data.getOrgId());
        entity.setOnlineTitle(data.getOnlineTitle());
        entity.setOnlineSubtitle(data.getOnlineSubtitle());
        entity.setOnlineCover(data.getOnlineCover());
        entity.setOnlineType(data.getOnlineType());
        entity.setOrgLevel(data.getOrgLevel());
        entity.setStartTime(data.getStartTime());
        entity.setEndTime(data.getEndTime());
        entity.setAppCodes(data.getAppCodes());
        entity.setCreatorNum(data.getCreatorNum());
        entity.setCreateTime(data.getCreateTime());
        entity.setCompetitionEnabled(data.getCompetitionEnabled());
        entity.setEditFlag(data.getEditFlag());
        entity.setOrgNames(data.getOrgNames());
        entity.setOrgType(data.getOrgType());
        entity.setCreatorOrgName(data.getCreatorOrgName());
        entity.setJoinNum(data.getJoinNum());
        return entity;
    }

    public static CompetitionData convertToData(CompetitionEntity entity) {
        if (entity == null) {
            return null;
        }
        CompetitionData data = new CompetitionData();
        data.setId(entity.getId());
        data.setOrgId(entity.getOrgId());
        data.setOnlineTitle(entity.getOnlineTitle());
        data.setOnlineSubtitle(entity.getOnlineSubtitle());
        data.setOnlineCover(entity.getOnlineCover());
        data.setOnlineType(entity.getOnlineType());
        data.setOrgLevel(entity.getOrgLevel());
        data.setStartTime(entity.getStartTime());
        data.setEndTime(entity.getEndTime());
        data.setAppCodes(entity.getAppCodes());
        data.setCreatorNum(entity.getCreatorNum());
        data.setCreateTime(entity.getCreateTime());
        data.setCompetitionEnabled(entity.getCompetitionEnabled());
        data.setEditFlag(entity.getEditFlag());
        data.setOrgNames(entity.getOrgNames());
        data.setOrgType(entity.getOrgType());
        data.setCreatorOrgName(entity.getCreatorOrgName());
        data.setJoinNum(entity.getJoinNum());
        return data;
    }
}
