package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class WikiData {
    @SerializedName("orgId")
    private String orgId;

    @SerializedName("wikiId")
    private String wikiId;

    @SerializedName("wikiCover")
    private String wikiCover;

    @SerializedName("wikiName")
    private String wikiName;

    @SerializedName("wikiType")
    private String wikiType;

    @SerializedName("enabled")
    private Boolean enabled;

    @SerializedName("wikiSort")
    private String wikiSort;

    @SerializedName("wikiTypeNames")
    private List<String> wikiTypeNames;

    @SerializedName("appCodes")
    private List<String> appCodes;

    @SerializedName("createTime")
    private Long createTime;
}
