package com.adl.service.db;

import com.adl.service.entity.MeetGroupDetailsEntity;

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
public interface MeetGroupDetailsDao {

    @Query("select * from _meet_group_detail where _app_code = :appCode")
    List<MeetGroupDetailsEntity> getAll(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MeetGroupDetailsEntity> list);

    @Query("select * from _meet_group_detail where " +
            "_app_code = :appCode and " +
            "_meet_id = :meetId and " +
            "_meet_sport_skuId = :meetSportSkuId and " +
            "_competition_round = :competitionRound and " +
            "_team_unit_code = :teamUnitCode"
    )
    List<MeetGroupDetailsEntity> queryMeetDetailsByTeamCode(
            String appCode,
            String meetId,
            String meetSportSkuId,
            int competitionRound,
            String teamUnitCode
    );

    @Query("select * from _meet_group_detail where " +
            "_app_code = :appCode and " +
            "_meet_id = :meetId and " +
            "_meet_sport_skuId = :meetSportSkuId and " +
            "_competition_round = :competitionRound and " +
            "_team_unit_code = :teamUnitCode and " +
            "_group_no = :groupNo")
    List<MeetGroupDetailsEntity> queryMeetDetailsByGroupNo(
            String appCode,
            String meetId,
            String meetSportSkuId,
            int competitionRound,
            String teamUnitCode,
            int groupNo);

    @Query("delete from _meet_group_detail")
    void clearAll();


}
