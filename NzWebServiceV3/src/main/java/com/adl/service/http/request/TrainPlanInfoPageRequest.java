package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class TrainPlanInfoPageRequest extends BasePageRequest {

    /**
     * 关键字 训练计划/姓名
     */
    @SerializedName("keyword")
    private String keyword;

    /**
     * 机构id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 计划id
     */
    @SerializedName("uid")
    private String uid;

    /**
     * 状态 1：未开始 2:进行中 3：已结束
     */
    @SerializedName("timeStatus")
    private String timeStatus;

    /**
     * 年级id
     */
    @SerializedName("gradeUid")
    private String gradeUid;

    /**
     * 班级id
     */
    @SerializedName("classUid")
    private String classUid;

    /**
     * 计划日期
     */
    @SerializedName("trainPlanTime")
    private String trainPlanTime;

    /**
     * 运动日期
     */
    @SerializedName("sportTime")
    private String sportTime;

    /**
     * 计划开始时间
     */
    @SerializedName("trainPlanStartTime")
    private String trainPlanStartTime;

    /**
     * 计划结束时间
     */
    @SerializedName("trainPlanEndTime")
    private String trainPlanEndTime;

    /**
     * 运动开始时间
     */
    @SerializedName("sportStarTime")
    private String sportStarTime;

    /**
     * 运动结束时间
     */
    @SerializedName("sportEndTime")
    private String sportEndTime;

    /**
     * 学期编码
     */
    @SerializedName("acayearSemCode")
    private String acayearSemCode;

    /**
     * 特训人员id
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 特训计划id
     */
    @SerializedName("trainPlanIds")
    private List<String> trainPlanIds;

    private TrainPlanInfoPageRequest(Builder builder) {
        super(builder);
        this.keyword = builder.keyword;
        this.orgId = builder.orgId;
        this.uid = builder.uid;
        this.timeStatus = builder.timeStatus;
        this.gradeUid = builder.gradeUid;
        this.classUid = builder.classUid;
        this.trainPlanTime = builder.trainPlanTime;
        this.sportTime = builder.sportTime;
        this.trainPlanStartTime = builder.trainPlanStartTime;
        this.trainPlanEndTime = builder.trainPlanEndTime;
        this.sportStarTime = builder.sportStarTime;
        this.sportEndTime = builder.sportEndTime;
        this.acayearSemCode = builder.acayearSemCode;
        this.accountId = builder.accountId;
        this.trainPlanIds = builder.trainPlanIds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String keyword;
        private String orgId;
        private String uid;
        private String timeStatus;
        private String gradeUid;
        private String classUid;
        private String trainPlanTime;
        private String sportTime;
        private String trainPlanStartTime;
        private String trainPlanEndTime;
        private String sportStarTime;
        private String sportEndTime;
        private String acayearSemCode;
        private String accountId;
        private List<String> trainPlanIds;

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder timeStatus(String timeStatus) {
            this.timeStatus = timeStatus;
            return this;
        }

        public Builder gradeUid(String gradeUid) {
            this.gradeUid = gradeUid;
            return this;
        }

        public Builder classUid(String classUid) {
            this.classUid = classUid;
            return this;
        }

        public Builder trainPlanTime(String trainPlanTime) {
            this.trainPlanTime = trainPlanTime;
            return this;
        }

        public Builder sportTime(String sportTime) {
            this.sportTime = sportTime;
            return this;
        }

        public Builder trainPlanStartTime(String trainPlanStartTime) {
            this.trainPlanStartTime = trainPlanStartTime;
            return this;
        }

        public Builder trainPlanEndTime(String trainPlanEndTime) {
            this.trainPlanEndTime = trainPlanEndTime;
            return this;
        }

        public Builder sportStarTime(String sportStarTime) {
            this.sportStarTime = sportStarTime;
            return this;
        }

        public Builder sportEndTime(String sportEndTime) {
            this.sportEndTime = sportEndTime;
            return this;
        }

        public Builder acayearSemCode(String acayearSemCode) {
            this.acayearSemCode = acayearSemCode;
            return this;
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder trainPlanIds(List<String> trainPlanIds) {
            this.trainPlanIds = trainPlanIds;
            return this;
        }

        public TrainPlanInfoPageRequest build() {
            return new TrainPlanInfoPageRequest(this);
        }
    }
}