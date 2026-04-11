package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "_dict")
public class DictEntity {

    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    @ColumnInfo(name = "_dict_code")
    private String dictCode;

    @ColumnInfo(name = "_dict_value")
    private String dictValue;

    @ColumnInfo(name = "_dict_label")
    private String dictLabel;

    public DictEntity() {
        this.id = "";
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }
}
