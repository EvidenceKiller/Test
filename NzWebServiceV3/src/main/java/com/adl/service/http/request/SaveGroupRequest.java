package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class SaveGroupRequest extends BaseRequest {

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
     * 班级Id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 分组规则
     */
    @SerializedName("groupRule")
    private BaseGroupRule groupRule;

    /**
     * 运动Id
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 分组信息
     */
    @SerializedName("groups")
    private List<BaseGroup> groups;

    /**
     * 长跑分组模式 1-号牌 2-人脸 - 邓工要求加的字段
     */
    @SerializedName("longModeType")
    private Integer longModeType;

    private SaveGroupRequest(Builder builder) {
        super(builder);
        this.sceneCode = builder.sceneCode;
        this.ptPlanId = builder.ptPlanId;
        this.classId = builder.classId;
        this.groupRule = builder.groupRule;
        this.sportSkuId = builder.sportSkuId;
        this.groups = builder.groups;
        this.longModeType = builder.longModeType;
    }

    public static Builder builder(String sceneCode, List<BaseGroup> groups) {
        return new Builder(sceneCode, groups);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String sceneCode;
        private String ptPlanId;
        private String classId;
        private BaseGroupRule groupRule;
        private String sportSkuId;
        private List<BaseGroup> groups;
        private Integer longModeType;

        Builder(String sceneCode, List<BaseGroup> groups) {
            this.sceneCode = sceneCode;
            this.groups = groups;
        }

        public Builder ptPlanId(String ptPlanId) {
            this.ptPlanId = ptPlanId;
            return this;
        }

        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }

        public Builder groupRule(BaseGroupRule groupRule) {
            this.groupRule = groupRule;
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

        public SaveGroupRequest build() {
            return new SaveGroupRequest(this);
        }
    }
}