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
@Entity(tableName = "_file_upload_info")
public class FileUploadInfoEntity {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 机构信息（切换机构）
    @ColumnInfo(name = "_org_id")
    private String orgId;

    @ColumnInfo(name = "_local_path")
    private String localPath;

    // 上传状态
    @ColumnInfo(name = "_upload_status")
    private int uploadStatus;

    @ColumnInfo(name = "_remote_file_name")
    private String remoteFileName;

    @ColumnInfo(name = "_remote_url")
    private String remoteUrl;

    @ColumnInfo(name = "_remote_bucket")
    private String remoteBucket;

    @ColumnInfo(name = "_create_time")
    private long createTime;

    @ColumnInfo(name = "_remark")
    private String remark;
}