package com.adl.service.db;

import com.adl.service.db.entity.SportMeetGroupDetailEntity;

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
public interface SportMeetGroupDetailDao {

    @Query("select * from _sport_meet_group_detail where _app_code = :appCode")
    List<SportMeetGroupDetailEntity> getAll(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportMeetGroupDetailEntity> list);

    @Query("select * from _sport_meet_group_detail where " +
            "_app_code = :appCode and " +
            "_meet_id = :meetId and " +
            "_meet_sport_skuId = :meetSportSkuId and " +
            "_competition_round = :competitionRound and " +
            "_team_unit_code = :teamUnitCode"
    )
    List<SportMeetGroupDetailEntity> queryMeetDetailsByTeamCode(
            String appCode,
            String meetId,
            String meetSportSkuId,
            int competitionRound,
            String teamUnitCode
    );

    @Query("select * from _sport_meet_group_detail where " +
            "_app_code = :appCode and " +
            "_meet_id = :meetId and " +
            "_meet_sport_skuId = :meetSportSkuId and " +
            "_competition_round = :competitionRound and " +
            "_team_unit_code = :teamUnitCode and " +
            "_group_no = :groupNo")
    List<SportMeetGroupDetailEntity> queryMeetDetailsByGroupNo(
            String appCode,
            String meetId,
            String meetSportSkuId,
            int competitionRound,
            String teamUnitCode,
            int groupNo);

    @Query("delete from _sport_meet_group_detail where _app_code = :appCode")
    void clearByAppCode(String appCode);

    @Query("delete from _sport_meet_group_detail")
    void clearAll();


}
