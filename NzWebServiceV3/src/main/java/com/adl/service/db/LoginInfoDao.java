package com.adl.service.db;

import com.adl.service.db.entity.LoginInfoEntity;
import com.adl.service.db.entity.PlanEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface LoginInfoDao {

    @Query("SELECT * FROM _login_info LIMIT 1")
    LoginInfoEntity getLoginInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveLoginInfo(LoginInfoEntity entity);

    @Query("DELETE FROM _login_info")
    void clear();

    @Transaction
    default void saveLoginInfoWithClear(LoginInfoEntity entity) {
        clear();
        saveLoginInfo(entity);
    }
}
