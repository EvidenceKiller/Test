package com.adl.service.db.entity;

import com.adl.service.data.SportMeetSimpleClassData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class SportMeetSimpleClassEntity {
    private Integer id;

    /**
     * 机构Id
     */
    private String orgId;

    /**
     * 运动会Id
     */
    private String sportMeetId;

    /**
     * 报名人数
     */
    private Integer applyUserCount;

    /**
     * 班级Id
     */
    private String classId;
    private String createTime;
    private String updateTime;

    public static SportMeetSimpleClassEntity convertToEntity(SportMeetSimpleClassData data) {
        if (data == null) {
            return null;
        }
        SportMeetSimpleClassEntity entity = new SportMeetSimpleClassEntity();
        entity.setId(data.getId());
        entity.setOrgId(data.getOrgId());
        entity.setSportMeetId(data.getSportMeetId());
        entity.setApplyUserCount(data.getApplyUserCount());
        entity.setClassId(data.getClassId());
        entity.setCreateTime(data.getCreateTime());
        entity.setUpdateTime(data.getUpdateTime());
        return entity;
    }

    public static SportMeetSimpleClassData convertToData(SportMeetSimpleClassEntity entity) {
        if (entity == null) {
            return null;
        }
        SportMeetSimpleClassData data = new SportMeetSimpleClassData();
        data.setId(entity.getId());
        data.setOrgId(entity.getOrgId());
        data.setSportMeetId(entity.getSportMeetId());
        data.setApplyUserCount(entity.getApplyUserCount());
        data.setClassId(entity.getClassId());
        data.setCreateTime(entity.getCreateTime());
        data.setUpdateTime(entity.getUpdateTime());
        return data;
    }
}
