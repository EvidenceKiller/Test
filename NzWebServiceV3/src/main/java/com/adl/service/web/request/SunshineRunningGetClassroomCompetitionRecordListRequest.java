package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public final class SunshineRunningGetClassroomCompetitionRecordListRequest {

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

    /** 机构id */
    @SerializedName("orgId")
    private String orgId;

    /** 学级 */
    @SerializedName("gradeId")
    private String gradeId;

    /** 班级id */
    @SerializedName("classId")
    private String classId;

    /** 组别 */
    @SerializedName("multiPersonIndex")
    private Integer multiPersonIndex;

    /** 开始日期 */
    @SerializedName("startDate")
    private String startDate;

    /** 结束日期 */
    @SerializedName("endDate")
    private String endDate;

    /** 项目 */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /** 应用端 */
    @SerializedName("appCode")
    private String appCode;

    /** 关键字 */
    @SerializedName("keyword")
    private String keyword;

    private SunshineRunningGetClassroomCompetitionRecordListRequest(Builder builder) {
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
        this.orgId = builder.orgId;
        this.gradeId = builder.gradeId;
        this.classId = builder.classId;
        this.multiPersonIndex = builder.multiPersonIndex;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.sportSkuId = builder.sportSkuId;
        this.appCode = builder.appCode;
        this.keyword = builder.keyword;
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
        private String orgId;
        private String gradeId;
        private String classId;
        private Integer multiPersonIndex;
        private String startDate;
        private String endDate;
        private String sportSkuId;
        private String appCode;
        private String keyword;
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
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }
        public Builder gradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }
        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }
        public Builder multiPersonIndex(Integer multiPersonIndex) {
            this.multiPersonIndex = multiPersonIndex;
            return this;
        }
        public Builder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }
        public Builder endDate(String endDate) {
            this.endDate = endDate;
            return this;
        }
        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }
        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public SunshineRunningGetClassroomCompetitionRecordListRequest build() { return new SunshineRunningGetClassroomCompetitionRecordListRequest(this); }
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
    public String getOrgId() { return orgId; }
    public String getGradeId() { return gradeId; }
    public String getClassId() { return classId; }
    public Integer getMultiPersonIndex() { return multiPersonIndex; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getSportSkuId() { return sportSkuId; }
    public String getAppCode() { return appCode; }
    public String getKeyword() { return keyword; }
}