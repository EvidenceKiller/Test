package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class SportVideoData {
    /**
     * 视频说明
     */
    @SerializedName("videoDesc")
    private String videoDesc;

    /**
     * 视频相对地址
     */
    @SerializedName("videoUrl")
    private String videoUrl;

    /**
     * 视频封面图
     */
    @SerializedName("videoCover")
    private String videoCover;
}
