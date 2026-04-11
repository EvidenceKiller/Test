package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
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

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
}