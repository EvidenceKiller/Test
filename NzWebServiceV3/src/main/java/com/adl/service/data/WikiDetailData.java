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
public final class WikiDetailData {

    /**
     * 运动百科id，唯一
     */
    @SerializedName("wikiId")
    private String wikiId;

    /**
     * 运动百科封面地址
     */
    @SerializedName("wikiCover")
    private String wikiCover;

    /**
     * 标题
     */
    @SerializedName("wikiName")
    private String wikiName;

    /**
     * 内容形式
     */
    @SerializedName("wikiType")
    private String wikiType;

    /**
     * 百科内容
     */
    @SerializedName("wikiContext")
    private String wikiContext;

    /**
     * 状态
     */
    @SerializedName("enabled")
    private Boolean enabled;

    /**
     * 排序
     */
    @SerializedName("wikiSort")
    private String wikiSort;

    /**
     * 分类id
     */
    @SerializedName("wikiTypeIds")
    private List<String> wikiTypeIds;

    /**
     * 应用编码集合
     */
    @SerializedName("appCodes")
    private List<String> appCodes;

    /**
     * 创建时间
     */
    @SerializedName("createTime")
    private String createTime;

    /**
     * 百科视频列表
     */
    @SerializedName("wikiVideo")
    private List<WikiVideolData> wikiVideo;
}
