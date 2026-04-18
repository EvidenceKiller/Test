package com.adl.service.db.entity;

import com.adl.service.data.DictData;

import org.jetbrains.annotations.NotNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "_dict")
public final class DictEntity {
    public static final long EXPIRED_TIME = 1000L * 60L * 60L * 12L;

    @ColumnInfo(name = "_enable_sort")
    private Boolean enableSort;

    @ColumnInfo(name = "_module_str")
    private String moduleStr;

    /**
     * 主键 ID
     */
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    /**
     * 字典编码
     */
    @ColumnInfo(name = "_dict_code")
    private String dictCode;

    /**
     * 字典值
     */
    @ColumnInfo(name = "_dict_value")
    private String dictValue;

    /**
     * 字典显示
     */
    @ColumnInfo(name = "_dict_label")
    private String dictLabel;

    /**
     * 排序 升序
     */
    @ColumnInfo(name = "_sort_num")
    private Integer sortNum;

    /**
     * 是否默认
     */
    @ColumnInfo(name = "_default_flag")
    private String defaultFlag;

    /**
     * 扩展 json 扩展 json，存储扩展值可使用
     */
    @ColumnInfo(name = "_ext_data")
    private String extData;

    /**
     * 备注
     */
    @ColumnInfo(name = "_remark")
    private String remark;

    /**
     * 语言类型
     */
    @ColumnInfo(name = "_lang")
    private String lang;

    /**
     * 创建时间
     */
    @ColumnInfo(name = "_create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @ColumnInfo(name = "_update_time")
    private String updateTime;

    /**
     * 字典数据类型 1-string 2-number 3-bool
     */
    @ColumnInfo(name = "_data_type")
    private Integer dataType;

    @ColumnInfo(name = "_record_time", defaultValue = "(strftime('%s', 'now') * 1000)")
    private Long recordTime;

    public static DictEntity convertToEntity(DictData dictData) {
        if (dictData == null) {
            return null;
        }
        DictEntity entity = new DictEntity();
        entity.setId(dictData.getId());
        entity.setDictCode(dictData.getDictCode());
        entity.setDictValue(dictData.getDictValue());
        entity.setDictLabel(dictData.getDictLabel());
        entity.setSortNum(dictData.getSortNum());
        entity.setDefaultFlag(dictData.getDefaultFlag());
        entity.setExtData(dictData.getExtData());
        entity.setRemark(dictData.getRemark());
        entity.setLang(dictData.getLang());
        entity.setCreateTime(dictData.getCreateTime());
        entity.setUpdateTime(dictData.getUpdateTime());
        entity.setDataType(dictData.getDataType());
        entity.setEnableSort(dictData.getEnableSort());
        entity.setModuleStr(dictData.getModuleStr());
        return entity;
    }

    public static DictData convertToData(DictEntity entity) {
        if (entity == null) {
            return null;
        }
        DictData data = new DictData();
        data.setEnableSort(entity.getEnableSort());
        data.setModuleStr(entity.getModuleStr());
        data.setId(entity.getId());
        data.setDictCode(entity.getDictCode());
        data.setDictValue(entity.getDictValue());
        data.setDictLabel(entity.getDictLabel());
        data.setSortNum(entity.getSortNum());
        data.setDefaultFlag(entity.getDefaultFlag());
        data.setExtData(entity.getExtData());
        data.setRemark(entity.getRemark());
        data.setLang(entity.getLang());
        data.setCreateTime(entity.getCreateTime());
        data.setUpdateTime(entity.getUpdateTime());
        data.setDataType(entity.getDataType());
        return data;
    }
}
