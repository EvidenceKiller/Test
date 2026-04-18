package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class ResTypeData {

    /**
     * 主键ID
     */
    @SerializedName("id")
    private String id;

    /**
     * 分类名称
     */
    @SerializedName("typeName")
    private String typeName;

    /**
     * 排序
     */
    @SerializedName("sort")
    private Integer sort;

    /**
     * 机构id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 1 - 备课助手 2-校本资源
     */
    @SerializedName("resType")
    private Integer resType;

    /**
     * 创建时间
     */
    @SerializedName("createTime")
    private String createTime;

    /**
     * 更新时间
     */
    @SerializedName("updateTime")
    private String updateTime;
}
