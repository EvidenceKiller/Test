package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class DictMapData {

    @SerializedName("dict")
    private String dict;

    @SerializedName("rightKey")
    private String rightKey;

    @SerializedName("wrongKey")
    private String wrongKey;

    @SerializedName("type")
    private String type;
}
