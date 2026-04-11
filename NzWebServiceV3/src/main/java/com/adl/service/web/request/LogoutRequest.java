package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class LogoutRequest {
    /**
     * 登录的token
     */
    @SerializedName("authHeader")
    private String authHeader;

    private LogoutRequest(Builder builder) {
        this.authHeader = builder.authHeader;
    }

    public static Builder builder(String authHeader) {
        return new Builder(authHeader);
    }

    public static class Builder {
        private String authHeader;

        Builder(String authHeader) {
            this.authHeader = authHeader;
        }

        public LogoutRequest build() {
            return new LogoutRequest(this);
        }
    }

    public String getAuthHeader() {
        return authHeader;
    }
}