package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.SceneSportEntity;
import com.adl.service.entity.SportPlanStudentEntity;
import com.adl.service.entity.StudentEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface SceneSportDao {
    @Query("select * from _scene_sport where _app_code = :appCode")
    List<SceneSportEntity> getAll(String appCode);

    //select * from _scene_sport where ',' || _app_codes || ',' LIKE '%"' || '107103' || '"%'
    @Query("select * from _scene_sport where ',' || _app_codes || ',' LIKE '%\"' || :appCode || '\"%'")
    List<SceneSportEntity> getAllByAppCOde(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SceneSportEntity> list);

    @Query("select * from _scene_sport where _app_code = :appCode and _scene_id = :sceneId")
    List<SceneSportEntity> querySportBySceneId(String appCode, String sceneId);

    @Query("delete from _scene_sport")
    void clearAll();

    @Query("select * from _scene_sport where _app_code = :appCode and _sport_sku_id = :sportSkuId limit 1")
    SceneSportEntity querySportBySportSkuId(String appCode, String sportSkuId);

    @Query("select * from _scene_sport where _app_code = :appCode and _scene_id = :sceneId and _sport_sku_id = :sportSkuId limit 1")
    SceneSportEntity querySportBySceneAndSkuId(String appCode, String sceneId, String sportSkuId);
}
