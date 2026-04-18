package com.adl.service.db;

import com.adl.service.db.entity.CompetitionEntity;

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
public interface CompetitionDao {

    @Query("select * from _competition")
    List<CompetitionEntity> getAll();

    @Query("select * from _competition limit :noOfRows,:rowNum")
    List<CompetitionEntity> getAllByPage(int noOfRows, int rowNum);

    @Query("select * from _competition LIMIT :pageIndex,:pageSize")
    List<CompetitionEntity> getByPage(int pageIndex, int pageSize);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCompetitionList(List<CompetitionEntity> list);

    @Query("delete from _competition")
    void clearAll();
}
