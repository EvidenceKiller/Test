package com.adl.service.db;

import com.adl.service.db.entity.PlanEntity;

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
public interface PlanDao {

    @Query("select * from _sport_plan")
    List<PlanEntity> getAll();

    @Query("select * from _sport_plan limit :noOfRows,:rowNum")
    List<PlanEntity> getAllByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where _start_time > strftime('%s','now')*1000")
    List<PlanEntity> queryPlanStatusNew();

    @Query("select * from _sport_plan where _start_time > strftime('%s','now')*1000 limit :noOfRows,:rowNum")
    List<PlanEntity> queryPlanStatusNewByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where (strftime('%s','now')*1000 between _start_time and _end_time)")
    List<PlanEntity> queryPlanStatusDoing();

    @Query("select * from _sport_plan where (strftime('%s','now')*1000 between _start_time and _end_time) limit :noOfRows,:rowNum")
    List<PlanEntity> queryPlanStatusDoingByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where _end_time < strftime('%s','now')*1000")
    List<PlanEntity> queryPlanStatusDone();

    @Query("select * from _sport_plan where _end_time < strftime('%s','now')*1000 limit :noOfRows,:rowNum")
    List<PlanEntity> queryPlanStatusDoneByPage(int noOfRows, int rowNum);

    @Query("select * from _sport_plan where _plan_id = :planId limit 1")
    PlanEntity queryPlanById(String planId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlanList(List<PlanEntity> list);

    @Query("delete from _sport_plan")
    void clearAll();

    @Query("select * from _sport_plan WHERE (:planStatus='' or :planStatus='0' or _status=:planStatus) LIMIT :pageIndex,:pageSize")
    List<PlanEntity> getByPage(int pageIndex, int pageSize, String planStatus);

}
