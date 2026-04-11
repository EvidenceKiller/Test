package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class LoginRequest {
    @SerializedName("accountNum")
    private String accountNum;

    @SerializedName("password")
    private String password;

    private LoginRequest(Builder builder) {
        this.accountNum = builder.accountNum;
        this.password = builder.password;
    }

    public static Builder builder(String accountNum, String password) {
        return new Builder(accountNum, password);
    }

    public static class Builder {
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

    public String getAccountNum() {
        return accountNum;
    }

    public String getPassword() {
        return password;
    }
}