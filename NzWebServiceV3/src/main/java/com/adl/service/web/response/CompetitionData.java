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
public final class CompetitionData {
    /**
     * 唯一标识符
     */
    @SerializedName("id")
    private String id;

    /**
     * 机构ID
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 在线标题
     */
    @SerializedName("onlineTitle")
    private String onlineTitle;

    /**
     * 在线副标题
     */
    @SerializedName("onlineSubtitle")
    private String onlineSubtitle;

    /**
     * 在线封面图片地址
     */
    @SerializedName("onlineCover")
    private String onlineCover;

    /**
     * 在线类型
     */
    @SerializedName("onlineType")
    private Integer onlineType;

    /**
     * 机构级别
     */
    @SerializedName("orgLevel")
    private Integer orgLevel;

    /**
     * 开始时间
     */
    @SerializedName("startTime")
    private Long startTime;

    /**
     * 结束时间
     */
    @SerializedName("endTime")
    private Long endTime;

    /**
     * 应用代码列表
     */
    @SerializedName("appCodes")
    private List<String> appCodes;

    /**
     * 创建者编码
     */
    @SerializedName("creatorNum")
    private String creatorNum;

    /**
     * 创建时间
     */
    @SerializedName("createTime")
    private Long createTime;

    /**
     * 竞赛启用状态
     */
    @SerializedName("competitionEnabled")
    private Integer competitionEnabled;

    /**
     * 编辑标志
     */
    @SerializedName("editFlag")
    private Boolean editFlag;

    /**
     * 机构名称列表
     */
    @SerializedName("orgNames")
    private List<String> orgNames;

    /**
     * 机构类型
     */
    @SerializedName("orgType")
    private Integer orgType;

    /**
     * 创建者机构名称
     */
    @SerializedName("creatorOrgName")
    private String creatorOrgName;

    /**
     * 参与人数
     */
    @SerializedName("joinNum")
    private Integer joinNum;
}
