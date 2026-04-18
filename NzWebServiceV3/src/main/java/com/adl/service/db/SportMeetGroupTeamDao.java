package com.adl.service.db;

import com.adl.service.db.entity.SportMeetGroupTeamEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * ProjectName: AiPowerAuth
 * <p>
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface SportMeetGroupTeamDao {

    @Query("select * from _sport_meet_group_team where _app_code = :appCode")
    List<SportMeetGroupTeamEntity> getAll(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportMeetGroupTeamEntity> list);

    @Query("select * from _sport_meet_group_team where " +
            "_app_code = :appCode and " +
            "_meet_id = :meetId and " +
            "_meet_sport_skuId = :meetSportSkuId and " +
            "_sport_sku_id = :sportSkuId and " +
            "_competition_round = :competitionRound and " +
            "( :competitionStatus =-1 or _competition_status = :competitionStatus)  "
    )
    List<SportMeetGroupTeamEntity> queryMeetGroupTeamById(String appCode, String meetId, String meetSportSkuId, String sportSkuId, int competitionRound, int competitionStatus);

    @Query("delete from _sport_meet_group_team where _app_code = :appCode")
    void clearByAppCode(String appCode);

    @Query("delete from _sport_meet_group_team")
    void clearAll();

}
