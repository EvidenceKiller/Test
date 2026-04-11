package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class SportImageData {
    /**
     * 图片相对地址
     */
    @SerializedName("imageUrl")
    private String imageUrl;

    /**
     * 运动分析图片说明
     */
    @SerializedName("motionAnalysisImageDesc")
    private String motionAnalysisImageDesc;

    /**
     * 图片说明
     */
    @SerializedName("imageDesc")
    private String imageDesc;

    /**
     * 分组编号
     */
    @SerializedName("groupIndex")
    private String groupIndex;
}
