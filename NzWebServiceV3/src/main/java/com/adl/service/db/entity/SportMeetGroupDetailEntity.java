package com.adl.service.db.entity;

import com.adl.service.data.SportMeetGroupDetailData;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "_sport_meet_group_detail")
public class SportMeetGroupDetailEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 应用编码【手动添加】
    @ColumnInfo(name = "_app_code")
    private String appCode;

    //  运动会id【手动添加】
    @ColumnInfo(name = "_meet_id")
    private String meetId;

    //  运动会项目skuId【手动添加】
    @ColumnInfo(name = "_meet_sport_skuId")
    private String meetSportSkuId;

    //  轮次【手动添加】
    @ColumnInfo(name = "_competition_round")
    private int competitionRound;

    //  分组code
    @ColumnInfo(name = "_team_unit_code")
    private String teamUnitCode;

    //  分组名称
    @ColumnInfo(name = "_team_unit_name")
    private String teamUnitName;

    //  组号
    @ColumnInfo(name = "_group_no")
    private int groupNo;

    //  识别类型  参考字典编码：adl_tis_competition_meet_identify_type（0跑道识别 1运动员编码 2序号识别）
    @ColumnInfo(name = "_identify_type")
    private int identifyType;

    //  跑道号
    @ColumnInfo(name = "_runway_code")
    private String runwayCode;

    //  运动员编号
    @ColumnInfo(name = "_number_code")
    private String numberCode;

    //  序号
    @ColumnInfo(name = "_serial_code")
    private String serialCode;

    //  运动员编码
    @ColumnInfo(name = "_sport_account_num")
    private String sportAccountNum;

    //  运动员姓名
    @ColumnInfo(name = "_account_name")
    private String accountName;

    //  运动员id
    @ColumnInfo(name = "_account_id")
    private String accountId;

    //  班级id
    @ColumnInfo(name = "_class_id")
    private String classId;

    //  班级名称
    @ColumnInfo(name = "_class_name")
    private String className;

    //  头像
    @ColumnInfo(name = "_account_avatar")
    private String accountAvatar;

    //  运动结果
    @ColumnInfo(name = "_sport_result")
    private String sportResult;

    //  手动数据
    public void setManualData(String appCode, String meetId, String meetSportSkuId, int competitionRound) {
        this.appCode = appCode;
        this.meetId = meetId;
        this.meetSportSkuId = meetSportSkuId;
        this.competitionRound = competitionRound;
    }

    public String getIdentifyCode() {
        String code = "";
        //  识别类型  参考字典编码：adl_tis_competition_meet_identify_type（0跑道识别 1运动员编码 2序号识别）
        switch (identifyType) {
            //  跑道识别
            case 0:
                code = runwayCode;
                break;
            //  运动员编码
            case 1:
                code = numberCode;
                break;
            //  序号识别
            case 2:
                code = serialCode;
                break;
        }

        return code;
    }

    public static SportMeetGroupDetailEntity convertToEntity(SportMeetGroupDetailData data, String appCode) {
        if (data == null) {
            return null;
        }
        SportMeetGroupDetailEntity entity = new SportMeetGroupDetailEntity();
        entity.setAppCode(appCode);
        entity.setMeetId(data.getMeetId());
        entity.setMeetSportSkuId(data.getMeetSportSkuId());
        entity.setCompetitionRound(data.getCompetitionRound());
        entity.setTeamUnitCode(data.getTeamUnitCode());
        entity.setTeamUnitName(data.getTeamUnitName());
        entity.setGroupNo(data.getGroupNo());
        entity.setIdentifyType(data.getIdentifyType());
        entity.setRunwayCode(data.getRunwayCode());
        entity.setNumberCode(data.getNumberCode());
        entity.setSerialCode(data.getSerialCode());
        entity.setSportAccountNum(data.getSportAccountNum());
        entity.setAccountName(data.getAccountName());
        entity.setAccountId(data.getAccountId());
        entity.setClassId(data.getClassId());
        entity.setClassName(data.getClassName());
        entity.setAccountAvatar(data.getAccountAvatar());
        entity.setSportResult(data.getSportResult());
        return entity;
    }

    public static SportMeetGroupDetailData convertToData(SportMeetGroupDetailEntity entity) {
        if (entity == null) {
            return null;
        }
        SportMeetGroupDetailData data = new SportMeetGroupDetailData();
        data.setMeetId(entity.getMeetId());
        data.setMeetSportSkuId(entity.getMeetSportSkuId());
        data.setCompetitionRound(entity.getCompetitionRound());
        data.setTeamUnitCode(entity.getTeamUnitCode());
        data.setTeamUnitName(entity.getTeamUnitName());
        data.setGroupNo(entity.getGroupNo());
        data.setIdentifyType(entity.getIdentifyType());
        data.setRunwayCode(entity.getRunwayCode());
        data.setNumberCode(entity.getNumberCode());
        data.setSerialCode(entity.getSerialCode());
        data.setSportAccountNum(entity.getSportAccountNum());
        data.setAccountName(entity.getAccountName());
        data.setAccountId(entity.getAccountId());
        data.setClassId(entity.getClassId());
        data.setClassName(entity.getClassName());
        data.setAccountAvatar(entity.getAccountAvatar());
        data.setSportResult(entity.getSportResult());
        return data;
    }
}
