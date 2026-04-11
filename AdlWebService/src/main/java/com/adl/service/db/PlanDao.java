package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.SportPlanEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface PlanDao {

    @Query("select * from _sport_plan")
    List<SportPlanEntity> getAll();

    @Query("select * from _sport_plan limit :noOfRows,:rowNum")
    List<SportPlanEntity> getAllByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where _start_time > strftime('%s','now')*1000")
    List<SportPlanEntity> queryPlanStatusNew();

    @Query("select * from _sport_plan where _start_time > strftime('%s','now')*1000 limit :noOfRows,:rowNum")
    List<SportPlanEntity> queryPlanStatusNewByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where (strftime('%s','now')*1000 between _start_time and _end_time)")
    List<SportPlanEntity> queryPlanStatusDoing();

    @Query("select * from _sport_plan where (strftime('%s','now')*1000 between _start_time and _end_time) limit :noOfRows,:rowNum")
    List<SportPlanEntity> queryPlanStatusDoingByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where _end_time < strftime('%s','now')*1000")
    List<SportPlanEntity> queryPlanStatusDone();

    @Query("select * from _sport_plan where _end_time < strftime('%s','now')*1000 limit :noOfRows,:rowNum")
    List<SportPlanEntity> queryPlanStatusDoneByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where _plan_id = :planId limit 1")
    SportPlanEntity queryPlanById(String planId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportPlanEntity> list);

    @Query("delete from _sport_plan")
    void clearAll();

    @Query("select * from _sport_plan WHERE (:planStatus='' or :planStatus='0' or _status=:planStatus) LIMIT :pageIndex,:pageSize")
    List<SportPlanEntity> getByPage(int pageIndex, int pageSize, String planStatus);

}
