package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户信息（与接口文档字段对应）。
 */
@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class LoginInfoAccountData {
    /**
     * 租户id
     */
    @SerializedName("tenantOrgId")
    private String tenantOrgId;

    /**
     * 租户代码
     */
    @SerializedName("tenantOrgCode")
    private String tenantOrgCode;

    /**
     * 用户id
     */
    @SerializedName("accountId")
    private String accountId;

    /**
     * 机构id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 机构编码
     */
    @SerializedName("orgCode")
    private String orgCode;

    /**
     * 机构名称
     */
    @SerializedName("orgName")
    private String orgName;

    /**
     * 机构类型
     */
    @SerializedName("orgType")
    private String orgType;

    /**
     * 当前执行身份
     */
    @SerializedName("roleType")
    private Integer roleType;

    /**
     * 用户登录账号
     */
    @SerializedName("accountNum")
    private String accountNum;

    /**
     * 登录账号名
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 账号手机号
     */
    @SerializedName("accountPhoneNum")
    private String accountPhoneNum;

    /**
     * 头像
     */
    @SerializedName("accountAvatar")
    private String accountAvatar;

    /**
     * 工号
     */
    @SerializedName("jobNum")
    private String jobNum;

    /**
     * 卡号
     */
    @SerializedName("cardNum")
    private String cardNum;

    /**
     * 入学年
     */
    @SerializedName("enterYear")
    private String enterYear;

    /**
     * 是否当前登录用户 true: 是 false: 不是
     */
    @SerializedName("presentFlag")
    private Boolean presentFlag;

    /**
     * 当前用户是否激活 true: 是 false: 不是
     */
    @SerializedName("enabled")
    private Boolean enabled;

    /**
     * 角色集
     */
    @SerializedName("roles")
    private List<LoginInfoAccountRoleData> roles;

    /**
     * 年级 label: 名称 value: id
     */
    @SerializedName("grade")
    private LoginInfoAccountGradeData grade;

    /**
     * 部门集合 label: 名称 value: id extra:类型
     */
    @SerializedName("departments")
    private List<String> departments;

    /**
     * 小孩名称 label: 名称 value: id extra:子女信息
     */
    @SerializedName("children")
    private List<String> children;

    /**
     * 班级集合 label: 名称 value: id
     */
    @SerializedName("classes")
    private List<String> classes;
}
