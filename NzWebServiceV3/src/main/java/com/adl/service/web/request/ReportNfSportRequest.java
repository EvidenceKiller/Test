package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ReportNfSportRequest {

    /**
     * 记录标识码
     */
    @SerializedName("id")
    private String id;

    /**
     * 记录标识id
     */
    @SerializedName("recordId")
    private String recordId;

    /**
     * 学年标识码
     */
    @SerializedName("academicYearCode")
    private Integer academicYearCode;

    /**
     * 用户标识码
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 产品应用标识码
     */
    @SerializedName("appCode")
    private String appCode;

    /**
     * 运动完美个数
     */
    @SerializedName("perfectCount")
    private Integer perfectCount;

    /**
     * 运动优秀个数
     */
    @SerializedName("bravoCount")
    private Integer bravoCount;

    /**
     * 运动良好个数
     */
    @SerializedName("goodeviceount")
    private Integer goodeviceount;

    /**
     * 扩展JSON
     */
    @SerializedName("extraJson")
    private String extraJson;

    /**
     * 赛事信息
     */
    @SerializedName("competitionAthletics")
    private BaseCompetitionAthletics competitionAthletics;

    /**
     * 指标信息
     */
    @SerializedName("sportIndicators")
    private List<BaseSportIndicator> sportIndicators;

    /**
     * 竞技赛信息
     */
    @SerializedName("sportMeet")
    private BaseSportMeet sportMeet;

    /**
     * 组织测试信息
     */
    @SerializedName("sportPlan")
    private BaseSportPlan sportPlan;

    /**
     * 特训信息
     */
    @SerializedName("trainPlan")
    private BaseTrainPlan trainPlan;

    /**
     * 记录有效标识位
     */
    @SerializedName("invalidFlag")
    private Integer invalidFlag;

    /**
     * 多人运动Id
     */
    @SerializedName("multiPersonId")
    private String multiPersonId;

    /**
     * 多人运动排名
     */
    @SerializedName("multiPersonRank")
    private Integer multiPersonRank;

    /**
     * 多人运动机位排序
     */
    @SerializedName("multiPersonIndex")
    private Integer multiPersonIndex;

    /**
     * 原始运动结果
     */
    @SerializedName("originalSportResult")
    private String originalSportResult;

    /**
     * 场所id
     */
    @SerializedName("placeId")
    private String placeId;

    /**
     * 场所名称
     */
    @SerializedName("placeName")
    private String placeName;

    /**
     * 学期标识码
     */
    @SerializedName("semesterId")
    private String semesterId;

    /**
     * 来源 0（正常来源） 1（平台手动录入）
     */
    @SerializedName("sourceType")
    private String sourceType;

    /**
     * 运动统计字段 例：跳绳类为个数 阳光跑为mm
     */
    @SerializedName("sportCount")
    private Long sportCount;

    /**
     * 运动开始时间，时间戳不能为空
     */
    @SerializedName("sportStartTime")
    private String sportStartTime;

    /**
     * 运动结束时间，时间戳不能为空
     */
    @SerializedName("sportEndTime")
    private String sportEndTime;

    /**
     * 运动图片集
     */
    @SerializedName("sportImages")
    private List<BaseSportImage> sportImages;

    /**
     * 运动视频集
     */
    @SerializedName("sportVideos")
    private List<BaseSportVideo> sportVideos;

    /**
     * 运动结果
     */
    @SerializedName("sportResult")
    private String sportResult;

    /**
     * 运动场景代码
     */
    @SerializedName("sportSceneCode")
    private String sportSceneCode;

    /**
     * 运动得分
     */
    @SerializedName("sportScore")
    private Double sportScore;

    /**
     * 运动sku编码
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 运动标签
     */
    @SerializedName("sportSkuSubType")
    private String sportSkuSubType;

    /**
     * 运动时长
     */
    @SerializedName("sportTime")
    private Long sportTime;

    /**
     * 运动单位编码
     */
    @SerializedName("sportUnitCode")
    private Integer sportUnitCode;

    /**
     * 是否违规不能为空 1:违规
     */
    @SerializedName("sportViolationStatus")
    private Integer sportViolationStatus;

    private ReportNfSportRequest(Builder builder) {
        this.id = builder.id;
        this.recordId = builder.recordId;
        this.academicYearCode = builder.academicYearCode;
        this.accountId = builder.accountId;
        this.appCode = builder.appCode;
        this.perfectCount = builder.perfectCount;
        this.bravoCount = builder.bravoCount;
        this.goodeviceount = builder.goodeviceount;
        this.extraJson = builder.extraJson;
        this.competitionAthletics = builder.competitionAthletics;
        this.sportIndicators = builder.sportIndicators;
        this.sportMeet = builder.sportMeet;
        this.sportPlan = builder.sportPlan;
        this.trainPlan = builder.trainPlan;
        this.invalidFlag = builder.invalidFlag;
        this.multiPersonId = builder.multiPersonId;
        this.multiPersonRank = builder.multiPersonRank;
        this.multiPersonIndex = builder.multiPersonIndex;
        this.originalSportResult = builder.originalSportResult;
        this.placeId = builder.placeId;
        this.placeName = builder.placeName;
        this.semesterId = builder.semesterId;
        this.sourceType = builder.sourceType;
        this.sportCount = builder.sportCount;
        this.sportStartTime = builder.sportStartTime;
        this.sportEndTime = builder.sportEndTime;
        this.sportImages = builder.sportImages;
        this.sportVideos = builder.sportVideos;
        this.sportResult = builder.sportResult;
        this.sportSceneCode = builder.sportSceneCode;
        this.sportScore = builder.sportScore;
        this.sportSkuId = builder.sportSkuId;
        this.sportSkuSubType = builder.sportSkuSubType;
        this.sportTime = builder.sportTime;
        this.sportUnitCode = builder.sportUnitCode;
        this.sportViolationStatus = builder.sportViolationStatus;
    }

    public static Builder builder(String accountId, String appCode, Integer invalidFlag, Long sportCount, String sportStartTime, String sportEndTime, String sportResult, String sportSceneCode, String sportSkuId, String sportSkuSubType, Long sportTime) {
        return new Builder(accountId, appCode, invalidFlag, sportCount, sportStartTime, sportEndTime, sportResult, sportSceneCode, sportSkuId, sportSkuSubType, sportTime);
    }


    public static class Builder {
        private String id;
        private String recordId;
        private Integer academicYearCode;
        private String accountId;
        private String appCode;
        private Integer perfectCount;
        private Integer bravoCount;
        private Integer goodeviceount;
        private String extraJson;
        private BaseCompetitionAthletics competitionAthletics;
        private List<BaseSportIndicator> sportIndicators;
        private BaseSportMeet sportMeet;
        private BaseSportPlan sportPlan;
        private BaseTrainPlan trainPlan;
        private Integer invalidFlag;
        private String multiPersonId;
        private Integer multiPersonRank;
        private Integer multiPersonIndex;
        private String originalSportResult;
        private String placeId;
        private String placeName;
        private String semesterId;
        private String sourceType;
        private Long sportCount;
        private String sportStartTime;
        private String sportEndTime;
        private List<BaseSportImage> sportImages;
        private List<BaseSportVideo> sportVideos;
        private String sportResult;
        private String sportSceneCode;
        private Double sportScore;
        private String sportSkuId;
        private String sportSkuSubType;
        private Long sportTime;
        private Integer sportUnitCode;
        private Integer sportViolationStatus;

        Builder(String accountId, String appCode, int invalidFlag, long sportCount, String sportStartTime, String sportEndTime, String sportResult, String sportSceneCode, String sportSkuId, String sportSkuSubType, long sportTime) {
            this.accountId = accountId;
            this.appCode = appCode;
            this.invalidFlag = invalidFlag;
            this.sportCount = sportCount;
            this.sportStartTime = sportStartTime;
            this.sportEndTime = sportEndTime;
            this.sportResult = sportResult;
            this.sportSceneCode = sportSceneCode;
            this.sportSkuId = sportSkuId;
            this.sportSkuSubType = sportSkuSubType;
            this.sportTime = sportTime;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder recordId(String recordId) {
            this.recordId = recordId;
            return this;
        }

        public Builder academicYearCode(Integer academicYearCode) {
            this.academicYearCode = academicYearCode;
            return this;
        }

        public Builder perfectCount(Integer perfectCount) {
            this.perfectCount = perfectCount;
            return this;
        }

        public Builder bravoCount(Integer bravoCount) {
            this.bravoCount = bravoCount;
            return this;
        }

        public Builder goodeviceount(Integer goodeviceount) {
            this.goodeviceount = goodeviceount;
            return this;
        }

        public Builder extraJson(String extraJson) {
            this.extraJson = extraJson;
            return this;
        }

        public Builder competitionAthletics(BaseCompetitionAthletics competitionAthletics) {
            this.competitionAthletics = competitionAthletics;
            return this;
        }

        public Builder sportIndicators(List<BaseSportIndicator> sportIndicators) {
            this.sportIndicators = sportIndicators;
            return this;
        }

        public Builder sportMeet(BaseSportMeet sportMeet) {
            this.sportMeet = sportMeet;
            return this;
        }

        public Builder sportPlan(BaseSportPlan sportPlan) {
            this.sportPlan = sportPlan;
            return this;
        }

        public Builder trainPlan(BaseTrainPlan trainPlan) {
            this.trainPlan = trainPlan;
            return this;
        }

        public Builder multiPersonId(String multiPersonId) {
            this.multiPersonId = multiPersonId;
            return this;
        }

        public Builder multiPersonRank(Integer multiPersonRank) {
            this.multiPersonRank = multiPersonRank;
            return this;
        }

        public Builder multiPersonIndex(Integer multiPersonIndex) {
            this.multiPersonIndex = multiPersonIndex;
            return this;
        }

        public Builder originalSportResult(String originalSportResult) {
            this.originalSportResult = originalSportResult;
            return this;
        }

        public Builder placeId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Builder placeName(String placeName) {
            this.placeName = placeName;
            return this;
        }

        public Builder semesterId(String semesterId) {
            this.semesterId = semesterId;
            return this;
        }

        public Builder sourceType(String sourceType) {
            this.sourceType = sourceType;
            return this;
        }

        public Builder sportImages(List<BaseSportImage> sportImages) {
            this.sportImages = sportImages;
            return this;
        }

        public Builder sportVideos(List<BaseSportVideo> sportVideos) {
            this.sportVideos = sportVideos;
            return this;
        }

        public Builder sportScore(Double sportScore) {
            this.sportScore = sportScore;
            return this;
        }

        public Builder sportUnitCode(Integer sportUnitCode) {
            this.sportUnitCode = sportUnitCode;
            return this;
        }

        public Builder sportViolationStatus(Integer sportViolationStatus) {
            this.sportViolationStatus = sportViolationStatus;
            return this;
        }

        public ReportNfSportRequest build() {
            return new ReportNfSportRequest(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getRecordId() {
        return recordId;
    }

    public Integer getAcademicYearCode() {
        return academicYearCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAppCode() {
        return appCode;
    }

    public Integer getPerfectCount() {
        return perfectCount;
    }

    public Integer getBravoCount() {
        return bravoCount;
    }

    public Integer getGoodeviceount() {
        return goodeviceount;
    }

    public String getExtraJson() {
        return extraJson;
    }

    public BaseCompetitionAthletics getCompetitionAthletics() {
        return competitionAthletics;
    }

    public List<BaseSportIndicator> getSportIndicators() {
        return sportIndicators;
    }

    public BaseSportMeet getSportMeet() {
        return sportMeet;
    }

    public BaseSportPlan getSportPlan() {
        return sportPlan;
    }

    public BaseTrainPlan getTrainPlan() {
        return trainPlan;
    }

    public Integer getInvalidFlag() {
        return invalidFlag;
    }

    public String getMultiPersonId() {
        return multiPersonId;
    }

    public Integer getMultiPersonRank() {
        return multiPersonRank;
    }

    public Integer getMultiPersonIndex() {
        return multiPersonIndex;
    }

    public String getOriginalSportResult() {
        return originalSportResult;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public Long getSportCount() {
        return sportCount;
    }

    public String getSportStartTime() {
        return sportStartTime;
    }

    public String getSportEndTime() {
        return sportEndTime;
    }

    public List<BaseSportImage> getSportImages() {
        return sportImages;
    }

    public List<BaseSportVideo> getSportVideos() {
        return sportVideos;
    }

    public String getSportResult() {
        return sportResult;
    }

    public String getSportSceneCode() {
        return sportSceneCode;
    }

    public Double getSportScore() {
        return sportScore;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public String getSportSkuSubType() {
        return sportSkuSubType;
    }

    public Long getSportTime() {
        return sportTime;
    }

    public Integer getSportUnitCode() {
        return sportUnitCode;
    }

    public Integer getSportViolationStatus() {
        return sportViolationStatus;
    }
}