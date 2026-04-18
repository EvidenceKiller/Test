package com.adl.service.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
@Data
@NoArgsConstructor
@Entity(tableName = "_file_down_info")
public class FileDownInfoEntity {
    @PrimaryKey(autoGenerate = true)
    private long lid;

    @ColumnInfo(name = "_type")
    private int type;

    @ColumnInfo(name = "_url")
    private String url;

    @ColumnInfo(name = "_file_name")
    private String fileName;

    @ColumnInfo(name = "_local_path")
    private String localPath;

    @ColumnInfo(name = "_content_length")
    private long contentLength;
}