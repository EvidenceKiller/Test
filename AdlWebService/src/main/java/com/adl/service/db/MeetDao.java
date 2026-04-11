package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.SportMeetingEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface MeetDao {

    @Query("select * from _sport_meet where _app_code = :appCode")
    List<SportMeetingEntity> getAll(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode limit :noOfRows,:rowNum")
    List<SportMeetingEntity> getAllByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and _start_time > strftime('%s', 'now')*1000")
    List<SportMeetingEntity> queryMeetStatusNew(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode and _start_time > strftime('%s', 'now')*1000 limit :noOfRows,:rowNum")
    List<SportMeetingEntity> queryMeetStatusNewByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and (strftime('%s','now')*1000 between _start_time and _end_time)")
    List<SportMeetingEntity> queryMeetStatusDoing(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode and (strftime('%s','now')*1000 between _start_time and _end_time) limit :noOfRows,:rowNum")
    List<SportMeetingEntity> queryMeetStatusDoingByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and _end_time < strftime('%s','now')*1000")
    List<SportMeetingEntity> queryMeetStatusDone(String appCode);

    @Query("select * from _sport_meet where _app_code = :appCode and _end_time < strftime('%s','now')*1000 limit :noOfRows,:rowNum")
    List<SportMeetingEntity> queryMeetStatusDoneByPage(String appCode,int noOfRows, int rowNum);

    @Query("select * from _sport_meet where _app_code = :appCode and _id = :id limit 1")
    SportMeetingEntity queryMeetById(String appCode,String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportMeetingEntity> list);

    @Query("delete from _sport_meet")
    void clearAll();

}
