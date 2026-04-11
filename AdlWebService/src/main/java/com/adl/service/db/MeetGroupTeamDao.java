package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.MeetGroupTeamEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * <p>
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface MeetGroupTeamDao {

    @Query("select * from _meet_group_team where _app_code = :appCode")
    List<MeetGroupTeamEntity> getAll(String appCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MeetGroupTeamEntity> list);

    @Query("select * from _meet_group_team where " +
            "_app_code = :appCode and " +
            "_meet_id = :meetId and " +
            "_meet_sport_skuId = :meetSportSkuId and " +
            "_sport_sku_id = :sportSkuId and " +
            "_competition_round = :competitionRound and " +
            "( :competitionStatus =-1 or _competition_status = :competitionStatus)  "
    )
    List<MeetGroupTeamEntity> queryMeetGroupTeamById(String appCode, String meetId, String meetSportSkuId, String sportSkuId, int competitionRound, int competitionStatus);

    @Query("delete from _meet_group_team")
    void clearAll();

}
