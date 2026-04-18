package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseGroup {

    /**
     * 分组ID
     */
    @SerializedName("groupId")
    private String groupId;

    /**
     * 分组班级ID
     */
    @SerializedName("groupClassId")
    private String groupClassId;

    /**
     * 分组名称
     */
    @SerializedName("groupName")
    private String groupName;

    /**
     * 分组序号
     */
    @SerializedName("groupNumber")
    private String groupNumber;

    /**
     * 分组账户信息列表
     */
    @SerializedName("groupAccountInfos")
    private List<BaseGroupAccountInfo> groupAccountInfos;
}
