package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class LoginData {

    /**
     * token
     */
    @SerializedName("access_token")
    private String accessToken;

    /**
     * token类型
     */
    @SerializedName("token_type")
    private Integer tokenType;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("expires_in")
    private Double expiresIn;

    @SerializedName("scope")
    private String scope;
}
