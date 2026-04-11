package com.adl.service.entity;

import androidx.room.ColumnInfo;

/**
 * 仅年级查询
 */
public class CollegeQueryInfo {
    @ColumnInfo(name = "_college_id")
    private String id;
    @ColumnInfo(name = "_college_name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
