package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class SportSkuPageRequest extends BasePageRequest {

    /** 运动性质 */
    @SerializedName("sportNature")
    private String sportNature;

    /** 运动类型编码 */
    @SerializedName("sportType")
    private String sportType;

    /** 关键字，根据项目/单项名称或代码模糊搜索 */
    @SerializedName("keyword")
    private String keyword;

    /** 模板标识 */
    @SerializedName("templateFlag")
    private Integer templateFlag;

    /** ID列表 */
    @SerializedName("ids")
    private List<String> ids;

    /** 应用编码列表 */
    @SerializedName("appCodes")
    private List<String> appCodes;

    /** 应用编码 */
    @SerializedName("appCode")
    private String appCode;

    /** 场景ID */
    @SerializedName("sceneId")
    private String sceneId;

    /** 删除标志 */
    @SerializedName("delFlag")
    private Boolean delFlag;

    /** 启用状态 */
    @SerializedName("enabled")
    private Boolean enabled;

    private SportSkuPageRequest(Builder builder) {
        super(builder);
        this.sportNature = builder.sportNature;
        this.sportType = builder.sportType;
        this.keyword = builder.keyword;
        this.templateFlag = builder.templateFlag;
        this.ids = builder.ids;
        this.appCodes = builder.appCodes;
        this.appCode = builder.appCode;
        this.sceneId = builder.sceneId;
        this.delFlag = builder.delFlag;
        this.enabled = builder.enabled;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String sportNature;
        private String sportType;
        private String keyword;
        private Integer templateFlag;
        private List<String> ids;
        private List<String> appCodes;
        private String appCode;
        private String sceneId;
        private Boolean delFlag;
        private Boolean enabled;
        public Builder sportNature(String sportNature) {
            this.sportNature = sportNature;
            return this;
        }
        public Builder sportType(String sportType) {
            this.sportType = sportType;
            return this;
        }
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }
        public Builder templateFlag(Integer templateFlag) {
            this.templateFlag = templateFlag;
            return this;
        }
        public Builder ids(List<String> ids) {
            this.ids = ids;
            return this;
        }
        public Builder appCodes(List<String> appCodes) {
            this.appCodes = appCodes;
            return this;
        }
        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }
        public Builder sceneId(String sceneId) {
            this.sceneId = sceneId;
            return this;
        }
        public Builder delFlag(Boolean delFlag) {
            this.delFlag = delFlag;
            return this;
        }
        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public SportSkuPageRequest build() { return new SportSkuPageRequest(this); }
    }
}