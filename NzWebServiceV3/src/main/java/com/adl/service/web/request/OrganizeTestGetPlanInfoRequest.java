package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public final class OrganizeTestGetPlanInfoRequest {

    @SerializedName("noPaging")
    private Boolean noPaging;

    @SerializedName("pageSize")
    private Long pageSize;

    @SerializedName("current")
    private Long current;

    @SerializedName("detail")
    private Boolean detail;

    @SerializedName("enableSort")
    private Boolean enableSort;

    @SerializedName("sorts")
    private List<Map<String, Object>> sorts;

    @SerializedName("sortsStr")
    private String sortsStr;

    @SerializedName("ignoreColumns")
    private List<String> ignoreColumns;

    @SerializedName("containColumns")
    private List<String> containColumns;

    @SerializedName("moduleStr")
    private String moduleStr;

    /** 计划ID */
    @SerializedName("planId")
    private String planId;

    /** 机构ID */
    @SerializedName("orgId")
    private String orgId;

    /** 测试类型 */
    @SerializedName("standardType")
    private String standardType;

    /** 学年 */
    @SerializedName("acayearCode")
    private String acayearCode;

    /** 状态 1未开始 2进行中 3已结束 */
    @SerializedName("status")
    private String status;

    /** 是否显示统计 */
    @SerializedName("showStatistic")
    private Boolean showStatistic;

    private OrganizeTestGetPlanInfoRequest(Builder builder) {
        this.noPaging = builder.noPaging;
        this.pageSize = builder.pageSize;
        this.current = builder.current;
        this.detail = builder.detail;
        this.enableSort = builder.enableSort;
        this.sorts = builder.sorts;
        this.sortsStr = builder.sortsStr;
        this.ignoreColumns = builder.ignoreColumns;
        this.containColumns = builder.containColumns;
        this.moduleStr = builder.moduleStr;
        this.planId = builder.planId;
        this.orgId = builder.orgId;
        this.standardType = builder.standardType;
        this.acayearCode = builder.acayearCode;
        this.status = builder.status;
        this.showStatistic = builder.showStatistic;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private Boolean noPaging;
        private Long pageSize;
        private Long current;
        private Boolean detail;
        private Boolean enableSort;
        private List<Map<String, Object>> sorts;
        private String sortsStr;
        private List<String> ignoreColumns;
        private List<String> containColumns;
        private String moduleStr;
        private String planId;
        private String orgId;
        private String standardType;
        private String acayearCode;
        private String status;
        private Boolean showStatistic;
        public Builder noPaging(Boolean noPaging) {
            this.noPaging = noPaging;
            return this;
        }
        public Builder pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }
        public Builder current(Long current) {
            this.current = current;
            return this;
        }
        public Builder detail(Boolean detail) {
            this.detail = detail;
            return this;
        }
        public Builder enableSort(Boolean enableSort) {
            this.enableSort = enableSort;
            return this;
        }
        public Builder sorts(List<Map<String, Object>> sorts) {
            this.sorts = sorts;
            return this;
        }
        public Builder sortsStr(String sortsStr) {
            this.sortsStr = sortsStr;
            return this;
        }
        public Builder ignoreColumns(List<String> ignoreColumns) {
            this.ignoreColumns = ignoreColumns;
            return this;
        }
        public Builder containColumns(List<String> containColumns) {
            this.containColumns = containColumns;
            return this;
        }
        public Builder moduleStr(String moduleStr) {
            this.moduleStr = moduleStr;
            return this;
        }
        public Builder planId(String planId) {
            this.planId = planId;
            return this;
        }
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }
        public Builder standardType(String standardType) {
            this.standardType = standardType;
            return this;
        }
        public Builder acayearCode(String acayearCode) {
            this.acayearCode = acayearCode;
            return this;
        }
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        public Builder showStatistic(Boolean showStatistic) {
            this.showStatistic = showStatistic;
            return this;
        }

        public OrganizeTestGetPlanInfoRequest build() { return new OrganizeTestGetPlanInfoRequest(this); }
    }

    public Boolean getNoPaging() { return noPaging; }
    public Long getPageSize() { return pageSize; }
    public Long getCurrent() { return current; }
    public Boolean getDetail() { return detail; }
    public Boolean getEnableSort() { return enableSort; }
    public List<Map<String, Object>> getSorts() { return sorts; }
    public String getSortsStr() { return sortsStr; }
    public List<String> getIgnoreColumns() { return ignoreColumns; }
    public List<String> getContainColumns() { return containColumns; }
    public String getModuleStr() { return moduleStr; }
    public String getPlanId() { return planId; }
    public String getOrgId() { return orgId; }
    public String getStandardType() { return standardType; }
    public String getAcayearCode() { return acayearCode; }
    public String getStatus() { return status; }
    public Boolean getShowStatistic() { return showStatistic; }
}