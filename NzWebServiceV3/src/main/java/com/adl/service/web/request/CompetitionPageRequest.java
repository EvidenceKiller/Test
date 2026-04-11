package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class CompetitionPageRequest extends BasePageRequest {

    /** 赛事类型 */
    @SerializedName("onlineType")
    private Integer onlineType;

    /** 机构ID */
    @SerializedName("orgId")
    private String orgId;

    /** 赛事级别 */
    @SerializedName("competitionLevel")
    private Integer competitionLevel;

    /** 应用编码 */
    @SerializedName("appCode")
    private String appCode;

    /** 开始时间，时间戳毫秒 */
    @SerializedName("startTime")
    private String startTime;

    /** 结束时间，时间戳毫秒 */
    @SerializedName("endTime")
    private String endTime;

    /** 赛事状态 1未开始 2进行中 3已结束 */
    @SerializedName("competitionEnabled")
    private Integer competitionEnabled;

    /** 名称/发布人模糊搜索 */
    @SerializedName("keyword")
    private String keyword;

    /** 学期Code */
    @SerializedName("acayearSemCode")
    private String acayearSemCode;

    /** 机构类型 */
    @SerializedName("orgType")
    private Integer orgType;

    /** 终端标识 */
    @SerializedName("terminalFlag")
    private Boolean terminalFlag;

    /** 账户ID */
    @SerializedName("accountId")
    private String accountId;

    /** 排序字段 0 createTime , 1 startTime */
    @SerializedName("sortField")
    private Integer sortField;

    private CompetitionPageRequest(Builder builder) {
        super(builder);
        this.onlineType = builder.onlineType;
        this.orgId = builder.orgId;
        this.competitionLevel = builder.competitionLevel;
        this.appCode = builder.appCode;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.competitionEnabled = builder.competitionEnabled;
        this.keyword = builder.keyword;
        this.acayearSemCode = builder.acayearSemCode;
        this.orgType = builder.orgType;
        this.terminalFlag = builder.terminalFlag;
        this.accountId = builder.accountId;
        this.sortField = builder.sortField;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private Integer onlineType;
        private String orgId;
        private Integer competitionLevel;
        private String appCode;
        private String startTime;
        private String endTime;
        private Integer competitionEnabled;
        private String keyword;
        private String acayearSemCode;
        private Integer orgType;
        private Boolean terminalFlag;
        private String accountId;
        private Integer sortField;
        public Builder onlineType(Integer onlineType) {
            this.onlineType = onlineType;
            return this;
        }
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }
        public Builder competitionLevel(Integer competitionLevel) {
            this.competitionLevel = competitionLevel;
            return this;
        }
        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }
        public Builder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }
        public Builder endTime(String endTime) {
            this.endTime = endTime;
            return this;
        }
        public Builder competitionEnabled(Integer competitionEnabled) {
            this.competitionEnabled = competitionEnabled;
            return this;
        }
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }
        public Builder acayearSemCode(String acayearSemCode) {
            this.acayearSemCode = acayearSemCode;
            return this;
        }
        public Builder orgType(Integer orgType) {
            this.orgType = orgType;
            return this;
        }
        public Builder terminalFlag(Boolean terminalFlag) {
            this.terminalFlag = terminalFlag;
            return this;
        }
        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }
        public Builder sortField(Integer sortField) {
            this.sortField = sortField;
            return this;
        }

        public CompetitionPageRequest build() { return new CompetitionPageRequest(this); }
    }
    public Integer getOnlineType() { return onlineType; }
    public String getOrgId() { return orgId; }
    public Integer getCompetitionLevel() { return competitionLevel; }
    public String getAppCode() { return appCode; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public Integer getCompetitionEnabled() { return competitionEnabled; }
    public String getKeyword() { return keyword; }
    public String getAcayearSemCode() { return acayearSemCode; }
    public Integer getOrgType() { return orgType; }
    public Boolean getTerminalFlag() { return terminalFlag; }
    public String getAccountId() { return accountId; }
    public Integer getSortField() { return sortField; }
}