package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class LoginInfoData {
    /**
     * 上级机构ID
     */
    @SerializedName("pid")
    private String pid;

    /**
     * 一级机构名称
     */
    @SerializedName("orgName")
    private String orgName;

    /**
     * 机构代码
     */
    @SerializedName("orgCode")
    private String orgCode;

    /**
     * 机构类型
     */
    @SerializedName("orgType")
    private Integer orgType;

    /**
     * 区域代码
     */
    @SerializedName("areaCodes")
    private String areaCodes;

    /**
     * 区域名称
     */
    @SerializedName("areaNames")
    private String areaNames;

    /**
     * 详细地址
     */
    @SerializedName("address")
    private String address;

    /**
     * 管理员名称
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 管理员账号
     */
    @SerializedName("accountNum")
    private String accountNum;

    /**
     * 机构LOGO
     */
    @SerializedName("orglogo")
    private String orglogo;

    /**
     * 联系方式
     */
    @SerializedName("tel")
    private String tel;

    /**
     * 邮箱
     */
    @SerializedName("email")
    private String email;

    /**
     * 机构ID
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 学制代码
     * 参考字典编码: adl_tis_academic_system_code
     */
    @SerializedName("eduCode")
    private String eduCode;

    /**
     * 管理员名称
     */
    @SerializedName("platformName")
    private String platformName;

    /**
     * 经度
     */
    @SerializedName("longitude")
    private String longitude;

    /**
     * 纬度
     */
    @SerializedName("latitude")
    private String latitude;

    /**
     * 机构设备通用管理密码
     */
    @SerializedName("deviceCommonPassword")
    private String deviceCommonPassword;

    /**
     * 启用标识
     * 参考字典编码: adl_tis_enabled
     */
    @SerializedName("enabled")
    private Boolean enabled;

    /**
     * 机构资源文件上传总大小限制
     */
    @SerializedName("fileSizeLimit")
    private Long fileSizeLimit;

    @SerializedName("porgName")
    private String porgName;
}
