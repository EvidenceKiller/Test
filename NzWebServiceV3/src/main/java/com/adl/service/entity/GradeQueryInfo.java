package com.adl.service.entity;

import androidx.room.ColumnInfo;
/**
 * 仅年级查询
 */
public class GradeQueryInfo {
    @ColumnInfo(name = "_grade_id")
    private String id;
    @ColumnInfo(name = "_grade_name")
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
