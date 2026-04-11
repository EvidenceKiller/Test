package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class AccountInfoData {

    /**
     * 账号ID编码
     */
    @SerializedName("id")
    private String id;

    /**
     * 租户id
     */
    @SerializedName("tenantId")
    private String tenantId;

    /**
     * 租户代码
     */
    @SerializedName("tenantCode")
    private String tenantCode;

    /**
     * 前台机构id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 账号名
     */
    @SerializedName("accountName")
    private String accountName;

    /**
     * 所属用户id
     */
    @SerializedName("userId")
    private String userId;

    /**
     * 头像 可以存储相对和绝对路径，建议相对路径
     */
    @SerializedName("accountAvatar")
    private String accountAvatar;

    /**
     * 账户账号
     */
    @SerializedName("accountNum")
    private String accountNum;

    /**
     * 账户密码
     */
    @SerializedName("accountPassword")
    private String accountPassword;

    /**
     * 账户手机号
     */
    @SerializedName("accountPhoneNum")
    private String accountPhoneNum;

    /**
     * 学号
     */
    @SerializedName("studentNum")
    private String studentNum;

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
     * 部门信息
     */
    @SerializedName("departmentCodes")
    private String departmentCodes;

    /**
     * 入学年份
     */
    @SerializedName("enterYear")
    private String enterYear;

    /**
     * 注册场所id
     */
    @SerializedName("registerPlaceId")
    private String registerPlaceId;

    /**
     * 注册场所名称
     */
    @SerializedName("registerPlaceName")
    private String registerPlaceName;

    /**
     * 注册appCode
     */
    @SerializedName("registerAppCode")
    private String registerAppCode;

    /**
     * 注册设备id
     */
    @SerializedName("registerDeviceId")
    private String registerDeviceId;

    /**
     * 注册设备sn
     */
    @SerializedName("registerDeviceSn")
    private String registerDeviceSn;

    /**
     * 创建者账号
     */
    @SerializedName("creatorNum")
    private String creatorNum;

    /**
     * 头像标识
     */
    @SerializedName("avatarFlag")
    private Boolean avatarFlag;

    /**
     * 记录标识
     */
    @SerializedName("recordFlag")
    private Boolean recordFlag;

    /**
     * 是否启用
     */
    @SerializedName("enabled")
    private Boolean enabled;

    /**
     * 删除标识位 0 可用 1 删除
     */
    @SerializedName("delFlag")
    private Boolean delFlag;

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
     * 拼音全称
     */
    @SerializedName("accountNamePinYin")
    private String accountNamePinYin;

    /**
     * 首字母拼音
     */
    @SerializedName("firstLetterPinYin")
    private String firstLetterPinYin;

    @SerializedName("accountId")
    private String accountId;

    @SerializedName("roleType")
    private Integer roleType;

    @SerializedName("orgType")
    private Integer orgType;

    /**
     * 业务平台-公众用户表
     */
    @SerializedName("user")
    private AccountUserData user;

    @SerializedName("departments")
    private List<Object> departments;

    @SerializedName("classes")
    private List<Object> classes;

    @SerializedName("roles")
    private List<Object> roles;

    @SerializedName("accountAccountRels")
    private List<Object> accountAccountRels;

    @SerializedName("minAppPermission")
    private Object minAppPermission;

    @SerializedName("acayearSemCode")
    private String acayearSemCode;

    @SerializedName("studentCode")
    private String studentCode;

    @SerializedName("faceImgUrl")
    private String faceImgUrl;

    @SerializedName("classId")
    private String classId;

    @SerializedName("sex")
    private Integer sex;
}
