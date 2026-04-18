package com.adl.service.db;

import com.adl.service.db.entity.FileDownInfoEntity;

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
public interface FileDownDao {

    @Query("select * from _file_down_info")
    List<FileDownInfoEntity> getAll();

    @Query("select * from _file_down_info where _type = :type")
    List<FileDownInfoEntity> getAllByType(int type);

    @Query("select count(*) from _file_down_info where _type = :type")
    int queryCountSizeByType(int type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FileDownInfoEntity entity);

    @Update
    void update(FileDownInfoEntity entity);

    @Query("select * from _file_down_info where _file_name = :fileName limit 1")
    FileDownInfoEntity queryInfoByFileName(String fileName);

    @Query("delete from _file_down_info")
    void clearAll();

    @Query("delete from _file_down_info where _type = :type")
    void clearAllByType(int type);

    @Query("delete from _file_down_info where _file_name in(:fileNames)")
    void clearByFileNames(List<String> fileNames);

}
