package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class SportCompetitionInfoEntity implements Serializable {

    //  赛事Id
    private String id;

    //  赛事标题
    private String onlineTitle;

    //  赛事副标题
    private String onlineSubtitle;

    //  赛事封面地址
    private String onlineCover;

    //  赛事封面地址
    private String coverCustom;

    //  开始时间
    private long startTime;

    //  结束时间
    private long endTime;

    //  测试范围班级id
    private List<String> competitionRange;

    //  组织名称
    private List<String> orgNames;

    //  排行规则，1-成绩
    private String rankRule;

    //  赛事项目
    private List<CompetitionItemEntity> competitionItem;

    //  赛事说明富文本
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOnlineTitle() {
        return onlineTitle;
    }

    public void setOnlineTitle(String onlineTitle) {
        this.onlineTitle = onlineTitle;
    }

    public String getOnlineSubtitle() {
        return onlineSubtitle;
    }

    public void setOnlineSubtitle(String onlineSubtitle) {
        this.onlineSubtitle = onlineSubtitle;
    }

    public String getOnlineCover() {
        return onlineCover;
    }

    public void setOnlineCover(String onlineCover) {
        this.onlineCover = onlineCover;
    }

    public String getCoverCustom() {
        return coverCustom;
    }

    public void setCoverCustom(String coverCustom) {
        this.coverCustom = coverCustom;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<String> getCompetitionRange() {
        return competitionRange;
    }

    public void setCompetitionRange(List<String> competitionRange) {
        this.competitionRange = competitionRange;
    }

    public List<String> getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(List<String> orgNames) {
        this.orgNames = orgNames;
    }

    public String getRankRule() {
        return rankRule;
    }

    public void setRankRule(String rankRule) {
        this.rankRule = rankRule;
    }

    public List<CompetitionItemEntity> getCompetitionItem() {
        return competitionItem;
    }

    public void setCompetitionItem(List<CompetitionItemEntity> competitionItem) {
        this.competitionItem = competitionItem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
