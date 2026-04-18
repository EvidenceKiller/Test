package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
