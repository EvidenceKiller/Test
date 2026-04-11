package com.adl.service.db;

import com.adl.service.entity.FileUploadInfoEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface FileUploadDao {

    @Query("select * from _file_upload_info")
    List<FileUploadInfoEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FileUploadInfoEntity entity);

    @Update
    void update(FileUploadInfoEntity entity);

    @Query("select * from _file_upload_info where _upload_status = :status limit 1")
    FileUploadInfoEntity queryOneFileStatus(int status);

    @Query("select * from _file_upload_info where _remote_file_name = :remoteFileName limit 1")
    FileUploadInfoEntity queryByRemoteFileName(String remoteFileName);

    @Query("select * from _file_upload_info where _local_path = :localPath limit 1")
    FileUploadInfoEntity queryByLocalPath(String localPath);

    @Query("delete from _file_upload_info where _remote_file_name in (:list)")
    void clearByRemoteFileName(List<String> list);

    @Query("delete from _file_upload_info")
    void clearAll();

}
