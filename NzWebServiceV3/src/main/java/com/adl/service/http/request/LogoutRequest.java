package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class LogoutRequest extends BaseRequest {
    /**
     * 登录的token
     */
    @SerializedName("authHeader")
    private String authHeader;

    private LogoutRequest(Builder builder) {
        super(builder);
        this.authHeader = builder.authHeader;
    }

    public static Builder builder(String authHeader) {
        return new Builder(authHeader);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        private String authHeader;

        Builder(String authHeader) {
            this.authHeader = authHeader;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public LogoutRequest build() {
            return new LogoutRequest(this);
        }
    }
}