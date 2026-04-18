package com.adl.service.db;


import com.adl.service.db.entity.StandardConfigEntity;

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

    @Query("select * from _standard_config where _standard_id = :standardId")
    List<StandardConfigEntity> queryStandardById(String standardId);

    @Query("delete from _standard_config")
    void clearAll();

}
