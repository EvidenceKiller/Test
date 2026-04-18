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
public final class CompetitionRankDetailData {
    /**
     * 唯一标识符
     */
    @SerializedName("id")
    private String id;

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
     * 自定义封面
     */
    @SerializedName("coverCustom")
    private String coverCustom;

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
     * 机构级别名称
     */
    @SerializedName("orgLevelName")
    private String orgLevelName;

    /**
     * 开始时间
     */
    @SerializedName("startTime")
    private String startTime;

    /**
     * 结束时间
     */
    @SerializedName("endTime")
    private String endTime;

    /**
     * 应用代码列表
     */
    @SerializedName("appCodes")
    private List<String> appCodes;

    /**
     * 竞赛范围
     */
    @SerializedName("competitionRange")
    private List<String> competitionRange;

    /**
     * 机构名称列表
     */
    @SerializedName("orgNames")
    private List<String> orgNames;

    /**
     * 排名规则
     */
    @SerializedName("rankRule")
    private Integer rankRule;

    /**
     * 竞赛项目列表
     */
    @SerializedName("competitionItem")
    private List<CompetitionRankDetailItemData> competitionItem;

    /**
     * 备注
     */
    @SerializedName("remark")
    private String remark;

    /**
     * 参与标志
     */
    @SerializedName("joinFlag")
    private Boolean joinFlag;

    /**
     * 机构类型
     */
    @SerializedName("orgType")
    private Integer orgType;

    /**
     * 竞赛启用状态
     */
    @SerializedName("competitionEnabled")
    private Integer competitionEnabled;

    /**
     * 机构ID
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 编辑标志
     */
    @SerializedName("editFlag")
    private Boolean editFlag;
}
