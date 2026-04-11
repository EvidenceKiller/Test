package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.TeacherEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface TeacherDao {

    @Query("select * from _teacher")
    List<TeacherEntity> getAll();

    @Query("select count (*) from _teacher")
    int countSize();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TeacherEntity> list);

    @Query("select * from _teacher where _account_phone_num = :phoneNum or _account_num = :phoneNum limit 1")
    TeacherEntity queryTeacherByPhoneNum(String phoneNum);

    @Query("select * from _teacher where _account_id = :accountId limit 1")
    TeacherEntity queryTeacherByAccountId(String accountId);

    @Query("select * from _teacher where _account_id in (:accountIds)")
    List<TeacherEntity> queryTeacherByAccountIds(List<String> accountIds);

    @Query("select * from _teacher where _face_data_list != 'null'")
    List<TeacherEntity> queryTeacherWithFaceData();

    @Query("select * from _teacher where  (:sex = '' or _user_sex = :sex) and (:keyWord = '' or (_account_name like '%' || :keyWord || '%' )) limit :noOfRows,:rowNum")
    List<TeacherEntity> queryTeacherByPage(int noOfRows, int rowNum, String sex, String keyWord);

    @Query("delete from _teacher")
    void clearAll();

    @Delete
    void clear(List<TeacherEntity> list);
}
