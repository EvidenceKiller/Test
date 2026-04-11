package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.SportRecordEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface RecordUploadDao {
    @Query("select * from _sport_record_upload")
    List<SportRecordEntity> queryAll();

    @Query("select * from _sport_record_upload where _app_code = :appCode")
    List<SportRecordEntity> queryAllByAppCode(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportRecordEntity> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SportRecordEntity item);

    @Query("delete from _sport_record_upload")
    void clearAll();

    @Query("delete from _sport_record_upload where _id = :id")
    void clearById(String id);

    @Query("delete from _sport_record_upload where _id in (:ids)")
    void clearByIds(String ids);

    @Delete
    void clear(List<SportRecordEntity> list);
}
