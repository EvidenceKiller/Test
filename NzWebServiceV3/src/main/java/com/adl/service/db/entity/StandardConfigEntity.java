package com.adl.service.db.entity;

import com.adl.service.db.converter.ListSportSkuSimpleEntityConverter;
import com.adl.service.data.StandardConfigData;

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
@Entity(tableName = "_standard_config")
@TypeConverters(value = {ListSportSkuSimpleEntityConverter.class})
public final class StandardConfigEntity {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    /**
     * 标准ID
     */
    @ColumnInfo(name = "_standard_id")
    private String standardId;

    /**
     * 标准名称
     */
    @ColumnInfo(name = "_standard_name")
    private String standardName;

    /**
     * 标准类型枚举值: ZK,TC
     */
    @ColumnInfo(name = "_standard_type")
    private String standardType;

    /**
     * 启停用状态枚举值: 0,1
     */
    @ColumnInfo(name = "_enabled")
    private String enabled;

    /**
     * 组织全部标志枚举值: 0,1
     */
    @ColumnInfo(name = "_org_all_flag")
    private String orgAllFlag;

    /**
     * 项目列表
     */
    @ColumnInfo(name = "_sport_sku_list")
    private List<SportSkuSimpleEntity> sportSkuList;

    /**
     * 区域名称列表
     */
    @ColumnInfo(name = "_area_names")
    private List<String> areaNames;

    /**
     * 创建时间
     */
    @ColumnInfo(name = "_create_time")
    private String createTime;

    public static StandardConfigEntity convertToEntity(StandardConfigData data) {
        if (data == null) {
            return null;
        }
        StandardConfigEntity entity = new StandardConfigEntity();
        entity.setStandardId(data.getStandardId());
        entity.setStandardName(data.getStandardName());
        entity.setStandardType(data.getStandardType());
        entity.setEnabled(data.getEnabled());
        entity.setOrgAllFlag(data.getOrgAllFlag());
        if (data.getSportSkuList() != null) {
            entity.setSportSkuList(data.getSportSkuList().stream()
                    .map(SportSkuSimpleEntity::convertToEntity)
                    .collect(Collectors.toList()));
        }
        entity.setAreaNames(data.getAreaNames());
        entity.setCreateTime(data.getCreateTime());
        return entity;
    }

    public static StandardConfigData convertToData(StandardConfigEntity entity) {
        if (entity == null) {
            return null;
        }
        StandardConfigData data = new StandardConfigData();
        data.setStandardId(entity.getStandardId());
        data.setStandardName(entity.getStandardName());
        data.setStandardType(entity.getStandardType());
        data.setEnabled(entity.getEnabled());
        data.setOrgAllFlag(entity.getOrgAllFlag());
        if (entity.getSportSkuList() != null) {
            data.setSportSkuList(entity.getSportSkuList().stream()
                    .map(SportSkuSimpleEntity::convertToData)
                    .collect(Collectors.toList()));
        }
        data.setAreaNames(entity.getAreaNames());
        data.setCreateTime(entity.getCreateTime());
        return data;
    }
}
