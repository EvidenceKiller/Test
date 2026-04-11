package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public final class SunshineRunningSportDetailListRequest {

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

    /** 学期Id */
    @SerializedName("semesterId")
    private String semesterId;

    /** 机构Id */
    @SerializedName("orgId")
    private String orgId;

    /** 日期 */
    @SerializedName("date")
    private String date;

    private SunshineRunningSportDetailListRequest(Builder builder) {
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
        this.semesterId = builder.semesterId;
        this.orgId = builder.orgId;
        this.date = builder.date;
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
        private String semesterId;
        private String orgId;
        private String date;
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
        public Builder semesterId(String semesterId) {
            this.semesterId = semesterId;
            return this;
        }
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }
        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public SunshineRunningSportDetailListRequest build() { return new SunshineRunningSportDetailListRequest(this); }
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
    public String getSemesterId() { return semesterId; }
    public String getOrgId() { return orgId; }
    public String getDate() { return date; }
}