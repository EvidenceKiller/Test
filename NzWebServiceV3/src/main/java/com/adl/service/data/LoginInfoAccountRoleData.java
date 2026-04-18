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
public final class LoginInfoAccountRoleData {
    /**
     * 租户uid
     */
    @SerializedName("tenantId")
    private String tenantId;

    /**
     * 角色id
     */
    @SerializedName("roleId")
    private String roleId;

    /**
     * 角色类型
     */
    @SerializedName("roleType")
    private String roleType;

    /**
     * 角色名
     */
    @SerializedName("roleName")
    private String roleName;

    /**
     * 角色名
     * 注意：注释可能不准确，根据字段名应为"是否是当前角色"等含义
     */
    @SerializedName("presentFlag")
    private Boolean presentFlag;

    /**
     * 是否为系统预制角色
     * true: 是
     * false: 否
     */
    @SerializedName("systemFlag")
    private Boolean systemFlag;

    /**
     * 菜单集
     */
    @SerializedName("menus")
    private List<LoginInfoAccountRoleMenuData> menus;
}
