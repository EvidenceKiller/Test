package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class DictData {
    @SerializedName("enableSort")
    private Boolean enableSort;

    @SerializedName("moduleStr")
    private String moduleStr;

    /**
     * 主键 ID
     */
    @SerializedName("id")
    private String id;

    /**
     * 字典编码
     */
    @SerializedName("dictCode")
    private String dictCode;

    /**
     * 字典值
     */
    @SerializedName("dictValue")
    private String dictValue;

    /**
     * 字典显示
     */
    @SerializedName("dictLabel")
    private String dictLabel;

    /**
     * 排序 升序
     */
    @SerializedName("sortNum")
    private Integer sortNum;

    /**
     * 是否默认
     */
    @SerializedName("defaultFlag")
    private String defaultFlag;

    /**
     * 扩展 json 扩展 json，存储扩展值可使用
     */
    @SerializedName("extData")
    private String extData;

    /**
     * 备注
     */
    @SerializedName("remark")
    private String remark;

    /**
     * 语言类型
     */
    @SerializedName("lang")
    private String lang;

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

    /**
     * 字典数据类型 1-string 2-number 3-bool
     */
    @SerializedName("dataType")
    private Integer dataType;
}
