package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseSportVideo {
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
