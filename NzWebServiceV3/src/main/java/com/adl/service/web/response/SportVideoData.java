package com.adl.service.web.response;

import com.adl.service.entity.SportImageEntity;
import com.adl.service.entity.SportVideoEntity;
import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
