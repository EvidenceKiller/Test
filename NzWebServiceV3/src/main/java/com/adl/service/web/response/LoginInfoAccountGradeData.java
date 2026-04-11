package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class LoginInfoAccountGradeData {
    /**
     */
    @SerializedName("label")
    private String label;

    /**
     */
    @SerializedName("value")
    private String value;
}
