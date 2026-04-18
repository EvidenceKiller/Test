package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class WikiTypeData {
    @SerializedName("id")
    private String id;

    @SerializedName("typeName")
    private String typeName;

    @SerializedName("typeSort")
    private String typeSort;

    @SerializedName("createTime")
    private Long createTime;
}
