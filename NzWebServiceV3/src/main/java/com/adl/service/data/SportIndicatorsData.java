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
public final class SportIndicatorsData {
    //  指标code
    @SerializedName("indicatorsCode")
    private String indicatorsCode;
    //  指标名称
    @SerializedName("indicatorsName")
    private String indicatorsName;
    //  指标值
    @SerializedName("indicatorsValue")
    private String indicatorsValue;
    //  指标描述
    @SerializedName("indicatorsRemark")
    private String indicatorsRemark;
    //  指标单位
    @SerializedName("indicatorsUnit")
    private String indicatorsUnit;
    //  指标视频
    @SerializedName("indicatorsVideos")
    private List<SportVideoData> indicatorsVideos;
    //  指标图片
    @SerializedName("indicatorsImages")
    private List<SportImageData> indicatorsImages;
}
