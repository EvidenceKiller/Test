package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class LoginInfoAccountRoleMenuData {
    /**
     * 菜单id
     */
    @SerializedName("menuId")
    private String menuId;

    /**
     * 菜单名
     */
    @SerializedName("menuName")
    private String menuName;

    /**
     * 功能菜单路径
     */
    @SerializedName("menuPath")
    private String menuPath;

    /**
     * 父级菜单id
     */
    @SerializedName("pid")
    private String pid;

    /**
     * 菜单类型 0：菜单 1：按钮
     */
    @SerializedName("menuType")
    private String menuType;

    /**
     * 菜单权限标识
     */
    @SerializedName("menuPermission")
    private String menuPermission;

    /**
     * 菜单使用平台
     */
    @SerializedName("platform")
    private String platform;

    /**
     * 是否是跳转菜单
     */
    @SerializedName("redirectFlag")
    private Integer redirectFlag;

    /**
     * 菜单排序标识符
     */
    @SerializedName("sortNum")
    private Integer sortNum;
}
