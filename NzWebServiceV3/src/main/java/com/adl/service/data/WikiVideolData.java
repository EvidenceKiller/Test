package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class WikiVideolData {
    /**
     * id
     */
    @SerializedName("id")
    private String id;

    /**
     * 运动百科id，唯一
     */
    @SerializedName("wikiId")
    private String wikiId;

    /**
     * 视频封面地址
     */
    @SerializedName("wikiVideoCover")
    private String wikiVideoCover;

    /**
     * 视频地址
     */
    @SerializedName("wikiVideoUrl")
    private String wikiVideoUrl;

    /**
     * 运动百科视频描述
     */
    @SerializedName("wikiVideoDesc")
    private String wikiVideoDesc;

    /**
     * 运动百科视频标题
     */
    @SerializedName("wikiVideoTitle")
    private String wikiVideoTitle;
}
