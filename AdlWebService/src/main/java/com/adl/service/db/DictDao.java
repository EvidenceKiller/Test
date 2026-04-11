package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.DictEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface DictDao {
    @Query("select * from _dict")
    List<DictEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DictEntity> list);

    @Query("select * from _dict where _dict_code = :dictCode")
    List<DictEntity> queryDictByDictCode(String dictCode);

    @Query("select _dict_label from _dict where _dict_code = :dictCode and _dict_value = :dictValue")
    String queryDictLabel(String dictCode, String dictValue);

    @Query("delete from _dict where _dict_code = :dictCode")
    void clearByDictCode(String dictCode);

    @Query("delete from _dict")
    void clearAll();

}
