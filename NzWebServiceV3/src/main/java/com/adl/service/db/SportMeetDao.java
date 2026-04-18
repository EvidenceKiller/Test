package com.adl.service.db;

import com.adl.service.db.entity.SportMeetEntity;

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
public interface SportMeetDao {

    @Query("select * from _sport_meet where _app_code = :appCode")
    List<SportMeetEntity> getAll(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode limit :noOfRows,:rowNum")
    List<SportMeetEntity> getAllByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and _start_time > strftime('%s', 'now')*1000")
    List<SportMeetEntity> queryMeetStatusNew(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode and _start_time > strftime('%s', 'now')*1000 limit :noOfRows,:rowNum")
    List<SportMeetEntity> queryMeetStatusNewByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and (strftime('%s','now')*1000 between _start_time and _end_time)")
    List<SportMeetEntity> queryMeetStatusDoing(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode and (strftime('%s','now')*1000 between _start_time and _end_time) limit :noOfRows,:rowNum")
    List<SportMeetEntity> queryMeetStatusDoingByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and _end_time < strftime('%s','now')*1000")
    List<SportMeetEntity> queryMeetStatusDone(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode and _end_time < strftime('%s','now')*1000 limit :noOfRows,:rowNum")
    List<SportMeetEntity> queryMeetStatusDoneByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and _id = :id limit 1")
    SportMeetEntity queryMeetById(String appCode,String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportMeetEntity> list);

    @Query("delete from _sport_meet where _app_code = :appCode")
    void clearByAppCode(String appCode);

    @Query("delete from _sport_meet")
    void clearAll();

}
