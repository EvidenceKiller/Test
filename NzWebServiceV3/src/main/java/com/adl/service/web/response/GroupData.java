package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.adl.service.web.request.BaseGroup;
import com.adl.service.web.request.BaseGroupRule;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class GroupData {

    /**
     * 场景 code
     */
    @SerializedName("sceneCode")
    private String sceneCode;

    /**
     * 组织测试 Id
     */
    @SerializedName("ptPlanId")
    private String ptPlanId;

    /**
     * 班级 Id
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 分组规则
     */
    @SerializedName("groupRule")
    private BaseGroupRule groupRule;

    /**
     * 运动 Id
     */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /**
     * 分组信息
     */
    @SerializedName("groups")
    private List<BaseGroup> groups;

    /**
     * 长跑分组模式 1-号牌 2-人脸 - 邓工要求加的字段
     */
    @SerializedName("longModeType")
    private Integer longModeType;
}
