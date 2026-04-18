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
public final class StandardConfigData {

    /**
     * 标准ID
     */
    @SerializedName("standardId")
    private String standardId;

    /**
     * 标准名称
     */
    @SerializedName("standardName")
    private String standardName;

    /**
     * 标准类型枚举值: ZK,TC
     */
    @SerializedName("standardType")
    private String standardType;

    /**
     * 启停用状态枚举值: 0,1
     */
    @SerializedName("enabled")
    private String enabled;

    /**
     * 组织全部标志枚举值: 0,1
     */
    @SerializedName("orgAllFlag")
    private String orgAllFlag;

    /**
     * 项目列表
     */
    @SerializedName("sportSkuList")
    private List<SportSkuSimpleData> sportSkuList;

    /**
     * 区域名称列表
     */
    @SerializedName("areaNames")
    private List<String> areaNames;

    /**
     * 创建时间
     */
    @SerializedName("createTime")
    private String createTime;
}
