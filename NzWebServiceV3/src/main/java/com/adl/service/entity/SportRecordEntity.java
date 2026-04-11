package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.adl.service.db.converter.ListSportImageConverter;
import com.adl.service.db.converter.ListSportIndicatorsConverter;
import com.adl.service.db.converter.ListSportVideoConverter;
import com.adl.service.db.converter.RecordCompetitionConverter;
import com.adl.service.db.converter.RecordMeetingConverter;
import com.adl.service.db.converter.RecordPlanConverter;
import com.adl.service.db.converter.SportTrainPlanConverter;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 运动记录
 */
@Entity(tableName = "_sport_record_upload")
@TypeConverters(value = {ListSportImageConverter.class, ListSportVideoConverter.class,
        RecordCompetitionConverter.class, RecordMeetingConverter.class,
        RecordPlanConverter.class, ListSportIndicatorsConverter.class,
        SportTrainPlanConverter.class})
public class SportRecordEntity implements Serializable {
    //  记录id(雪花算法生成)
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;
    //  用户账号id(非必须)
    @ColumnInfo(name = "_account_id")
    private String accountId;
    //  应用编码
    @ColumnInfo(name = "_app_code")
    private String appCode;
    //  运动场景代码
    @ColumnInfo(name = "_sport_scene_code")
    private String sportSceneCode;
    //  用户类型 0：游客 1：普通用户
    @ColumnInfo(name = "_account_type")
    private String accountType;
    //  设备id
    @ColumnInfo(name = "_device_id")
    private String deviceId;

    //  记录有效标识位(非必须) 1：无效 0：有效
    @ColumnInfo(name = "_invalid_flag")
    private int invalidFlag;
    //  运动sku编码，sku配置表
    @ColumnInfo(name = "_sport_sku_id")
    private String sportSkuId;
    //  运动单位Code不能为空
    @ColumnInfo(name = "_sport_unit")
    private String sportUnit;

    //  运动结果不能为空
    @ColumnInfo(name = "_sport_result")
    private String sportResult;
    //  是否违规 0：正常 1：违规
    @ColumnInfo(name = "_sport_violation_status")
    private int sportViolationStatus;
    //  运动违规次数
    @ColumnInfo(name = "_sport_violation_number")
    private String sportViolationNumber;
    //  运动次数(非必须)
    @ColumnInfo(name = "_sport_count")
    private String sportCount;
    //  运动得分(非必须)
    @ColumnInfo(name = "_sport_score")
    private String sportScore;
    //  运动等级(非必须)
    @ColumnInfo(name = "_sport_level")
    private String sportLevel;

    //  运动时长，单位毫秒
    @ColumnInfo(name = "_sport_time")
    private String sportTime;
    //  运动测试时间，时间戳不能为空
    @ColumnInfo(name = "_sport_test_time")
    private String sportTestTime;
    //  运动开始时间，时间戳不能为空
    @ColumnInfo(name = "_sport_start_time")
    private String sportStartTime;
    //  运动结束时间，时间戳不能为空
    @ColumnInfo(name = "_sport_end_time")
    private String sportEndTime;

    //  多人运动Id
    @ColumnInfo(name = "_multi_person_id")
    private String multiPersonId;
    //  多人运动排名
    @ColumnInfo(name = "_multi_person_rank")
    private int multiPersonRank;

    @ColumnInfo(name = "_multi_person_index", defaultValue = "0")
    private int multiPersonIndex;

    //  图片集
    @ColumnInfo(name = "_sport_images")
    private List<SportImageEntity> sportImages;
    //  视频集
    @ColumnInfo(name = "_sport_videos")
    private List<SportVideoEntity> sportVideos;

    //  赛事
    @ColumnInfo(name = "_sport_competition")
    private RecordCompetition competitionAthletics;
    //  运动会
    @ColumnInfo(name = "_sport_meet")
    private RecordMeeting sportMeet;
    //  组织测试
    @ColumnInfo(name = "_sport_plan")
    private RecordPlan sportPlan;

    //特训训练计划
    @ColumnInfo(name = "_sport_train_plan", defaultValue = "")
    public SportTrainPlan trainPlan;

    @ColumnInfo(name = "_sport_nature_code", defaultValue = "")
    public String sportNatureCodes;

    @ColumnInfo(name = "_sport_type_code", defaultValue = "")
    public String sportTypeCode;

    //  运动指标集合
    @ColumnInfo(name = "_sport_indicators")
    private List<SportIndicatorsEntity<?, ?>> sportIndicators;

    // 运动子项类型
    @ColumnInfo(name = "_sport_sku_sub_type", defaultValue = "")
    private String sportSkuSubType;

    @ColumnInfo(name = "_place_id", defaultValue = "")
    private String placeId;

    @ColumnInfo(name = "_place_name", defaultValue = "")
    private String placeName;

    @ColumnInfo(name = "_number", defaultValue = "0")
    private int number;

    @ColumnInfo(name = "_circle", defaultValue = "0")
    private int circle;
    @ColumnInfo(name = "_create_time", defaultValue = "0")
    private String createTime;
    @ColumnInfo(name = "_update_time", defaultValue = "0")
    private String updateTime;

    @ColumnInfo(name = "_report_version", defaultValue = "")
    private String reportVersion;

    @ColumnInfo(name = "_report_code", defaultValue = "")
    private String reportCode;

    public SportRecordEntity() {
    }

    @Ignore
    public SportRecordEntity(@NotNull String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSportSceneCode() {
        return sportSceneCode;
    }

    public void setSportSceneCode(String sportSceneCode) {
        this.sportSceneCode = sportSceneCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(int invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public int getSportViolationStatus() {
        return sportViolationStatus;
    }

    public void setSportViolationStatus(int sportViolationStatus) {
        this.sportViolationStatus = sportViolationStatus;
    }

    public String getSportViolationNumber() {
        return sportViolationNumber;
    }

    public void setSportViolationNumber(String sportViolationNumber) {
        this.sportViolationNumber = sportViolationNumber;
    }

    public String getSportUnit() {
        return sportUnit;
    }

    public void setSportUnit(String sportUnit) {
        this.sportUnit = sportUnit;
    }

    public String getSportCount() {
        return sportCount;
    }

    public void setSportCount(String sportCount) {
        this.sportCount = sportCount;
    }

    public String getSportScore() {
        return sportScore;
    }

    public void setSportScore(String sportScore) {
        this.sportScore = sportScore;
    }

    public String getSportLevel() {
        return sportLevel;
    }

    public void setSportLevel(String sportLevel) {
        this.sportLevel = sportLevel;
    }

    public String getSportTime() {
        return sportTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }

    public String getSportTestTime() {
        return sportTestTime;
    }

    public void setSportTestTime(String sportTestTime) {
        this.sportTestTime = sportTestTime;
    }

    public String getSportStartTime() {
        return sportStartTime;
    }

    public void setSportStartTime(String sportStartTime) {
        this.sportStartTime = sportStartTime;
    }

    public String getSportEndTime() {
        return sportEndTime;
    }

    public void setSportEndTime(String sportEndTime) {
        this.sportEndTime = sportEndTime;
    }

    public String getMultiPersonId() {
        return multiPersonId;
    }

    public void setMultiPersonId(String multiPersonId) {
        this.multiPersonId = multiPersonId;
    }

    public int getMultiPersonRank() {
        return multiPersonRank;
    }

    public void setMultiPersonRank(int multiPersonRank) {
        this.multiPersonRank = multiPersonRank;
    }

    public int getMultiPersonIndex() {
        return multiPersonIndex;
    }

    public String getReportVersion() {
        return reportVersion;
    }

    public void setReportVersion(String reportVersion) {
        this.reportVersion = reportVersion;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public void setMultiPersonIndex(int multiPersonIndex) {
        this.multiPersonIndex = multiPersonIndex;
    }

    public List<SportImageEntity> getSportImages() {
        if (sportImages == null) {
            sportImages = new ArrayList<>();
        }
        return sportImages;
    }

    public void setSportImages(List<SportImageEntity> sportImages) {
        this.sportImages = sportImages;
    }

    public List<SportVideoEntity> getSportVideos() {
        if (sportVideos == null) {
            sportVideos = new ArrayList<>();
        }
        return sportVideos;
    }

    public void setSportVideos(List<SportVideoEntity> sportVideos) {
        this.sportVideos = sportVideos;
    }

    public RecordCompetition getCompetitionAthletics() {
        return competitionAthletics;
    }

    public void setCompetitionAthletics(RecordCompetition competitionAthletics) {
        this.competitionAthletics = competitionAthletics;
    }

    public RecordMeeting getSportMeet() {
        if (sportMeet == null) {
            sportMeet = new RecordMeeting();
        }
        return sportMeet;
    }

    public void setSportMeet(RecordMeeting sportMeet) {
        this.sportMeet = sportMeet;
    }

    public RecordPlan getSportPlan() {
        if (sportPlan == null) {
            sportPlan = new RecordPlan();
        }
        return sportPlan;
    }

    public void setSportPlan(RecordPlan sportPlan) {
        this.sportPlan = sportPlan;
    }

    public String getSportNatureCodes() {
        return sportNatureCodes;
    }

    public void setSportNatureCodes(String sportNatureCodes) {
        this.sportNatureCodes = sportNatureCodes;
    }

    public String getSportTypeCode() {
        return sportTypeCode;
    }

    public void setSportTypeCode(String sportTypeCode) {
        this.sportTypeCode = sportTypeCode;
    }

    public SportTrainPlan getTrainPlan() {
        if (trainPlan == null) {
            trainPlan = new SportTrainPlan();
        }
        return trainPlan;
    }

    public void setTrainPlan(SportTrainPlan trainPlan) {
        this.trainPlan = trainPlan;
    }

    public String getSportSkuSubType() {
        return sportSkuSubType;
    }

    public void setSportSkuSubType(String sportSkuSubType) {
        this.sportSkuSubType = sportSkuSubType;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public List<SportIndicatorsEntity<?, ?>> getSportIndicators() {
        if (sportIndicators == null) {
            sportIndicators = new ArrayList<>();
        }
        return sportIndicators;
    }

    public void setSportIndicators(List<SportIndicatorsEntity<?, ?>> sportIndicators) {
        this.sportIndicators = sportIndicators;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }


    //  是否违规
    public boolean isViolation() {
        return sportViolationStatus == 1;
    }

    public static class RecordCompetition implements Serializable {
        //  赛事id（赛事必传）
        private String competitionId;
        //  赛事名称（赛事必传）
        private String competitionName;
        //  赛事运动id（赛事必传）
        private String competitionAthSportId;
        //  赛事运动名称（赛事必传）
        private String competitionAthSportName;
        //  打卡状态
        private boolean competitionCheckStatus;
        //  完成状态
        private boolean competitionTaskStatus;

        public String getCompetitionId() {
            return competitionId;
        }

        public void setCompetitionId(String competitionId) {
            this.competitionId = competitionId;
        }

        public String getCompetitionName() {
            return competitionName;
        }

        public void setCompetitionName(String competitionName) {
            this.competitionName = competitionName;
        }

        public String getCompetitionAthSportId() {
            return competitionAthSportId;
        }

        public void setCompetitionAthSportId(String competitionAthSportId) {
            this.competitionAthSportId = competitionAthSportId;
        }

        public String getCompetitionAthSportName() {
            return competitionAthSportName;
        }

        public void setCompetitionAthSportName(String competitionAthSportName) {
            this.competitionAthSportName = competitionAthSportName;
        }

        public boolean isCompetitionCheckStatus() {
            return competitionCheckStatus;
        }

        public void setCompetitionCheckStatus(boolean competitionCheckStatus) {
            this.competitionCheckStatus = competitionCheckStatus;
        }

        public boolean isCompetitionTaskStatus() {
            return competitionTaskStatus;
        }

        public void setCompetitionTaskStatus(boolean competitionTaskStatus) {
            this.competitionTaskStatus = competitionTaskStatus;
        }
    }

    public static class RecordMeeting implements Serializable {
        //  班级Id【团体】
        private String classId;
        //  tis_competition_meet_type  1团体 2个人
        private String meetGroupType;
        //  运动会id
        private String meetId;
        //  运动会运动员编码
        private String meetNumberCode;
        //  比赛轮次
        private String meetRound;
        //  运动会跑道号
        private String meetRunwayCode;
        //  运动会运动sku关系标识码【关联运动会列表-skuList-id】
        private String meetSportSkuId;
        //  运动会组别
        private String meetTeamNo;
        //  分组单位  学级_班级_性别
        private String teamUnitCode;
        //  序号
        private String serialNumber;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getMeetGroupType() {
            return meetGroupType;
        }

        public void setMeetGroupType(String meetGroupType) {
            this.meetGroupType = meetGroupType;
        }

        public String getMeetId() {
            return meetId;
        }

        public void setMeetId(String meetId) {
            this.meetId = meetId;
        }

        public String getMeetNumberCode() {
            return meetNumberCode;
        }

        public void setMeetNumberCode(String meetNumberCode) {
            this.meetNumberCode = meetNumberCode;
        }

        public String getMeetRound() {
            return meetRound;
        }

        public void setMeetRound(String meetRound) {
            this.meetRound = meetRound;
        }

        public String getMeetRunwayCode() {
            return meetRunwayCode;
        }

        public void setMeetRunwayCode(String meetRunwayCode) {
            this.meetRunwayCode = meetRunwayCode;
        }

        public String getMeetSportSkuId() {
            return meetSportSkuId;
        }

        public void setMeetSportSkuId(String meetSportSkuId) {
            this.meetSportSkuId = meetSportSkuId;
        }

        public String getMeetTeamNo() {
            return meetTeamNo;
        }

        public void setMeetTeamNo(String meetTeamNo) {
            this.meetTeamNo = meetTeamNo;
        }

        public String getTeamUnitCode() {
            return teamUnitCode;
        }

        public void setTeamUnitCode(String teamUnitCode) {
            this.teamUnitCode = teamUnitCode;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }
    }

    public static class RecordPlan implements Serializable {
        //  组织测试id（组织测试必传）
        private String ptPlanId;
        //  组织测试批次（组织测试必传）
        private String ptBatch;
        //  组织测试标准库id（组织测试必传）
        private String ptStandardId;
        //  组织测试标准得分（组织测试必传）
//        private String ptSportStandardScore;
        private String ptFlag;
        //  是否违规 0：正常 1：违规
        private int sportViolationStatus;
        //  运动违规次数
        private String sportViolationNumber;
        //  组织测试测试类型 0：校方组织 1：自助测试
        private int ptTestType;
        private String ptPlanName;

        public String getPtPlanName() {
            return ptPlanName;
        }

        public void setPtPlanName(String ptPlanName) {
            this.ptPlanName = ptPlanName;
        }

        public String getPtPlanId() {
            return ptPlanId;
        }

        public void setPtPlanId(String ptPlanId) {
            this.ptPlanId = ptPlanId;
        }

        public String getPtBatch() {
            return ptBatch;
        }

        public void setPtBatch(String ptBatch) {
            this.ptBatch = ptBatch;
        }

        public String getPtStandardId() {
            return ptStandardId;
        }

        public void setPtStandardId(String ptStandardId) {
            this.ptStandardId = ptStandardId;
        }

//        public String getPtSportStandardScore() {
//            return ptSportStandardScore;
//        }
//
//        public void setPtSportStandardScore(String ptSportStandardScore) {
//            this.ptSportStandardScore = ptSportStandardScore;
//        }

        public String getPtFlag() {
            return ptFlag;
        }

        public void setPtFlag(String ptFlag) {
            this.ptFlag = ptFlag;
        }

        public int getSportViolationStatus() {
            return sportViolationStatus;
        }

        public void setSportViolationStatus(int sportViolationStatus) {
            this.sportViolationStatus = sportViolationStatus;
        }

        public String getSportViolationNumber() {
            return sportViolationNumber;
        }

        public void setSportViolationNumber(String sportViolationNumber) {
            this.sportViolationNumber = sportViolationNumber;
        }

        public int getPtTestType() {
            return ptTestType;
        }

        public void setPtTestType(int ptTestType) {
            this.ptTestType = ptTestType;
        }
    }

    public static class SportTrainPlan implements Serializable {
        private String trainPlanId;
        private String trainPlanName;
        private String trainPlanSportId;
        private String trainPlanSportName;

        public String getTrainPlanId() {
            return trainPlanId;
        }

        public void setTrainPlanId(String trainPlanId) {
            this.trainPlanId = trainPlanId;
        }

        public String getTrainPlanName() {
            return trainPlanName;
        }

        public void setTrainPlanName(String trainPlanName) {
            this.trainPlanName = trainPlanName;
        }

        public String getTrainPlanSportId() {
            return trainPlanSportId;
        }

        public void setTrainPlanSportId(String trainPlanSportId) {
            this.trainPlanSportId = trainPlanSportId;
        }

        public String getTrainPlanSportName() {
            return trainPlanSportName;
        }

        public void setTrainPlanSportName(String trainPlanSportName) {
            this.trainPlanSportName = trainPlanSportName;
        }
    }
}
