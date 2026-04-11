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

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getRemoteFileName() {
        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        this.remoteFileName = remoteFileName;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getRemoteBucket() {
        return remoteBucket;
    }

    public void setRemoteBucket(String remoteBucket) {
        this.remoteBucket = remoteBucket;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}