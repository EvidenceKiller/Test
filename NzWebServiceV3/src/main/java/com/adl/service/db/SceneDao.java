package com.adl.service.db;


import com.adl.service.db.entity.SceneEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface SceneDao {
    @Query("select * from _scene where _app_code = :appCode")
    List<SceneEntity> getAll(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SceneEntity> list);

    @Query("select * from _scene where _app_code = :appCode and _id = :id limit 1")
    SceneEntity querySportById(String appCode, String id);

    @Query("delete from _scene where _app_code = :appCode")
    void clearByAppCode(String appCode);

    @Query("delete from _scene")
    void clearAll();
}
