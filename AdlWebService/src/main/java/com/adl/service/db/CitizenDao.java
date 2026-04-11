package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.CitizenEntity;

import java.util.List;

@Dao
public interface CitizenDao {
    @Query("select * from _citizen")
    List<CitizenEntity> getAll();

    @Query("select count (*) from _citizen")
    int countSize();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CitizenEntity> list);

    @Query("select * from _citizen where _account_phone_num = :phoneNum or _account_num = :phoneNum limit 1")
    CitizenEntity queryCitizenByPhoneNum(String phoneNum);

    @Query("select * from _citizen where _account_id = :accountId limit 1")
    CitizenEntity queryCitizenByAccountId(String accountId);

    @Query("select * from _citizen where _account_id in (:accountIds)")
    List<CitizenEntity> queryCitizenByAccountIds(List<String> accountIds);

    @Query("select * from _citizen where _face_data_list != 'null'")
    List<CitizenEntity> queryCitizenWithFaceData();

    @Query("select * from _citizen where  (:sex = '' or _user_sex = :sex) and (:keyWord = '' or (_account_name like '%' || :keyWord || '%' )) limit :noOfRows,:rowNum")
    List<CitizenEntity> queryCitizenByPage(int noOfRows, int rowNum, String sex, String keyWord);

    @Query("delete from _citizen")
    void clearAll();

    @Delete
    void clear(List<CitizenEntity> list);
}
