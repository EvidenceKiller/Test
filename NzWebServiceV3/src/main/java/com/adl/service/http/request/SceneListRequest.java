package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class SceneListRequest extends BaseRequest {

    @SerializedName("appCode")
    private String appCode;

    @SerializedName("enabled")
    private Boolean enabled;

    @SerializedName("sceneType")
    private String sceneType;

    private SceneListRequest(Builder builder) {
        super(builder);
        this.appCode = builder.appCode;
        this.enabled = builder.enabled;
        this.sceneType = builder.sceneType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String appCode;
        private Boolean enabled;
        private String sceneType;

        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder sceneType(String sceneType) {
            this.sceneType = sceneType;
            return this;
        }

        public SceneListRequest build() {
            return new SceneListRequest(this);
        }
    }
}