package com.adl.service.db;

import com.adl.service.db.entity.SportSkuEntity;

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
public interface SceneSportDao {
    @Query("select * from _sport_sku where _app_code = :appCode")
    List<SportSkuEntity> getAll(String appCode);

    //select * from _sport_sku where ',' || _app_codes || ',' LIKE '%"' || '107103' || '"%'
    @Query("select * from _sport_sku where ',' || _app_codes || ',' LIKE '%\"' || :appCode || '\"%'")
    List<SportSkuEntity> getAllByAppCOde(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportSkuEntity> list);

    @Query("select * from _sport_sku where _app_code = :appCode and _scene_id = :sceneId")
    List<SportSkuEntity> querySportBySceneId(String appCode, String sceneId);

    @Query("delete from _sport_sku")
    void clearAll();

    @Query("select * from _sport_sku where _app_code = :appCode and _sport_sku_id = :sportSkuId limit 1")
    SportSkuEntity querySportBySportSkuId(String appCode, String sportSkuId);

    @Query("select * from _sport_sku where _app_code = :appCode and _scene_id = :sceneId and _sport_sku_id = :sportSkuId limit 1")
    SportSkuEntity querySportBySceneAndSkuId(String appCode, String sceneId, String sportSkuId);
}
