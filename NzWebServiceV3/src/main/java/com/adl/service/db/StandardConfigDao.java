package com.adl.service.db;

import com.adl.service.entity.StandardConfigEntity;

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
public interface StandardConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<StandardConfigEntity> list);

    @Query("select * from _sport_standard where _standard_id = :standardId")
    List<StandardConfigEntity> queryStandardById(String standardId);

    @Query("select * from _sport_standard where _standard_id = :standardId and _sport_project_code = :sportProjectCode")
    List<StandardConfigEntity> queryStandardByIdAndProject(String standardId, String sportProjectCode);

    @Query("delete from _sport_standard")
    void clearAll();

}
