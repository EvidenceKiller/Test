package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class LoginRequest extends BaseRequest {
    @SerializedName("accountNum")
    private String accountNum;

    @SerializedName("password")
    private String password;

    private LoginRequest(Builder builder) {
        super(builder);
        this.accountNum = builder.accountNum;
        this.password = builder.password;
    }

    public static Builder builder(String accountNum, String password) {
        return new Builder(accountNum, password);
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String accountNum;
        private String password;

        Builder(String accountNum, String password) {
            this.accountNum = accountNum;
            this.password = password;
        }

        public LoginRequest build() {
            return new LoginRequest(this);
        }
    }
}