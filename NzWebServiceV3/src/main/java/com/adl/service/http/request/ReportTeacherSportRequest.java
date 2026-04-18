package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class ReportTeacherSportRequest extends BaseRequest {

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
     * 产品应用标识名称
     */
    @SerializedName("appName")
    private String appName;

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
     * 记录标识码
     */
    @SerializedName("id")
    private String id;

    /**
     * 记录有效标识位
     */
    @SerializedName("invalidFlag")
    private Integer invalidFlag;

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
    private String sportUnitCode;

    /**
     * 原始运动结果
     */
    @SerializedName("originalSportResult")
    private String originalSportResult;

    /**
     * 是否违规不能为空 1:违规
     */
    @SerializedName("sportViolationStatus")
    private Integer sportViolationStatus;

    private ReportTeacherSportRequest(Builder builder) {
        super(builder);
        this.accountId = builder.accountId;
        this.appCode = builder.appCode;
        this.appName = builder.appName;
        this.perfectCount = builder.perfectCount;
        this.bravoCount = builder.bravoCount;
        this.goodeviceount = builder.goodeviceount;
        this.extraJson = builder.extraJson;
        this.id = builder.id;
        this.invalidFlag = builder.invalidFlag;
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
        this.originalSportResult = builder.originalSportResult;
        this.sportViolationStatus = builder.sportViolationStatus;
    }

    public static Builder builder(String accountId, String appCode, String appName, Integer invalidFlag, Long sportCount, String sportStartTime, String sportEndTime, String sportResult, String sportSceneCode, String sportSkuId, String sportSkuSubType, Long sportTime) {
        return new Builder(accountId, appCode, appName, invalidFlag, sportCount, sportStartTime, sportEndTime, sportResult, sportSceneCode, sportSkuId, sportSkuSubType, sportTime);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String accountId;
        private String appCode;
        private String appName;
        private Integer perfectCount;
        private Integer bravoCount;
        private Integer goodeviceount;
        private String extraJson;
        private String id;
        private Integer invalidFlag;
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
        private String sportUnitCode;
        private String originalSportResult;
        private Integer sportViolationStatus;

        Builder(String accountId, String appCode, String appName, int invalidFlag, long sportCount, String sportStartTime, String sportEndTime, String sportResult, String sportSceneCode, String sportSkuId, String sportSkuSubType, long sportTime) {
            this.accountId = accountId;
            this.appCode = appCode;
            this.appName = appName;
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

        public Builder id(String id) {
            this.id = id;
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

        public Builder sportUnitCode(String sportUnitCode) {
            this.sportUnitCode = sportUnitCode;
            return this;
        }

        public Builder originalSportResult(String originalSportResult) {
            this.originalSportResult = originalSportResult;
            return this;
        }

        public Builder sportViolationStatus(Integer sportViolationStatus) {
            this.sportViolationStatus = sportViolationStatus;
            return this;
        }

        public ReportTeacherSportRequest build() {
            return new ReportTeacherSportRequest(this);
        }
    }
}