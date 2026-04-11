package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.adl.service.entity.TeacherSportLocalEntity;

import java.util.List;

/**
 * hhd
 * 2025/4/22
 */
@Dao
public interface TeacherSportDao {

    @Query("select * from _teacher_sport_local where _app_code = :appCode and (_upload_data_status = 0 or _upload_modify_status = 1)")
    List<TeacherSportLocalEntity> getSportRecordByNotUpload(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeacherSport(TeacherSportLocalEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeacherSport(List<TeacherSportLocalEntity> entity);

    @Update
    void updateTeacherSport(TeacherSportLocalEntity entity);

    @Query("delete from _teacher_sport_local")
    void clearAll();
}
