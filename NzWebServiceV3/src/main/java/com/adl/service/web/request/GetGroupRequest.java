package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class GetGroupRequest {

    /**
     * 场景code
     */
    @SerializedName("sceneCode")
    private String sceneCode;

    /**
     * 组织测试Id
     */
    @SerializedName("ptPlanId")
    private String ptPlanId;

    /**
     * 随堂测班级Id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 分组ID
     */
    @SerializedName("groupId")
    private String groupId;

    /**
     * 分组班级ID
     */
    @SerializedName("groupClassId")
    private String groupClassId;

    /**
     * 分组编号
     */
    @SerializedName("groupNumber")
    private String groupNumber;

    /**
     * 运动Id
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 长跑分组模式 - 邓工要求加的字段
     */
    @SerializedName("longModeType")
    private Integer longModeType;

    private GetGroupRequest(Builder builder) {
        this.sceneCode = builder.sceneCode;
        this.ptPlanId = builder.ptPlanId;
        this.classId = builder.classId;
        this.groupId = builder.groupId;
        this.groupClassId = builder.groupClassId;
        this.groupNumber = builder.groupNumber;
        this.sportSkuId = builder.sportSkuId;
        this.longModeType = builder.longModeType;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String sceneCode;
        private String ptPlanId;
        private String classId;
        private String groupId;
        private String groupClassId;
        private String groupNumber;
        private String sportSkuId;
        private Integer longModeType;

        public Builder sceneCode(String sceneCode) {
            this.sceneCode = sceneCode;
            return this;
        }

        public Builder ptPlanId(String ptPlanId) {
            this.ptPlanId = ptPlanId;
            return this;
        }

        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }

        public Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder groupClassId(String groupClassId) {
            this.groupClassId = groupClassId;
            return this;
        }

        public Builder groupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }

        public Builder longModeType(Integer longModeType) {
            this.longModeType = longModeType;
            return this;
        }

        public GetGroupRequest build() {
            return new GetGroupRequest(this);
        }
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public String getPtPlanId() {
        return ptPlanId;
    }

    public String getClassId() {
        return classId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupClassId() {
        return groupClassId;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public Integer getLongModeType() {
        return longModeType;
    }
}