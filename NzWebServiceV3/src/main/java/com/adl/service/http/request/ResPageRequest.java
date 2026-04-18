package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public final class ResPageRequest extends BasePageRequest {

    /** 资源id */
    @SerializedName("id")
    private String id;

    /** 启停用 参考枚举 adl_tis_enabled */
    @SerializedName("enabled")
    private Boolean enabled;

    /** 资源类型 参考枚举 adl_tis_res_type */
    @SerializedName("type")
    private Integer type;

    /** 开始日期 */
    @SerializedName("startTime")
    private String startTime;

    /** 结束日期 */
    @SerializedName("endTime")
    private String endTime;

    /** 资源标题 */
    @SerializedName("title")
    private String title;

    /** 账号 */
    @SerializedName("createNum")
    private String createNum;

    /** 资源类型 1-备课助手 2-校本资源 */
    @SerializedName("resType")
    private Integer resType;

    /** 应用端代码 */
    @SerializedName("appCode")
    private String appCode;

    /** 分类Id */
    @SerializedName("resTypeId")
    private String resTypeId;

    @SerializedName("ids")
    private List<String> ids;

    private ResPageRequest(Builder builder) {
        super(builder);
        this.id = builder.id;
        this.enabled = builder.enabled;
        this.type = builder.type;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.title = builder.title;
        this.createNum = builder.createNum;
        this.resType = builder.resType;
        this.appCode = builder.appCode;
        this.resTypeId = builder.resTypeId;
        this.ids = builder.ids;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String id;
        private Boolean enabled;
        private Integer type;
        private String startTime;
        private String endTime;
        private String title;
        private String createNum;
        private Integer resType;
        private String appCode;
        private String resTypeId;
        private List<String> ids;
        Builder(String id) {
            this.id = id;
        }
        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }
        public Builder type(Integer type) {
            this.type = type;
            return this;
        }
        public Builder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }
        public Builder endTime(String endTime) {
            this.endTime = endTime;
            return this;
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder createNum(String createNum) {
            this.createNum = createNum;
            return this;
        }
        public Builder resType(Integer resType) {
            this.resType = resType;
            return this;
        }
        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }
        public Builder resTypeId(String resTypeId) {
            this.resTypeId = resTypeId;
            return this;
        }
        public Builder ids(List<String> ids) {
            this.ids = ids;
            return this;
        }

        public ResPageRequest build() { return new ResPageRequest(this); }
    }
}