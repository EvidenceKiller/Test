package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
