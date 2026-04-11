package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 分页请求公共字段。
 */
public class BasePageRequest {

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
    private List<BaseSort> sorts;

    @SerializedName("sortsStr")
    private String sortsStr;

    @SerializedName("ignoreColumns")
    private List<String> ignoreColumns;

    @SerializedName("containColumns")
    private List<String> containColumns;

    @SerializedName("moduleStr")
    private String moduleStr;

    protected BasePageRequest() {
    }

    protected BasePageRequest(Builder<?> builder) {
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
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected Boolean noPaging;
        protected Long pageSize;
        protected Long current;
        protected Boolean detail;
        protected Boolean enableSort;
        protected List<BaseSort> sorts;
        protected String sortsStr;
        protected List<String> ignoreColumns;
        protected List<String> containColumns;
        protected String moduleStr;

        protected abstract T self();

        public T noPaging(Boolean noPaging) {
            this.noPaging = noPaging;
            return self();
        }

        public T pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return self();
        }

        public T current(Long current) {
            this.current = current;
            return self();
        }

        public T detail(Boolean detail) {
            this.detail = detail;
            return self();
        }

        public T enableSort(Boolean enableSort) {
            this.enableSort = enableSort;
            return self();
        }

        public T sorts(List<BaseSort> sorts) {
            this.sorts = sorts;
            return self();
        }

        public T sortsStr(String sortsStr) {
            this.sortsStr = sortsStr;
            return self();
        }

        public T ignoreColumns(List<String> ignoreColumns) {
            this.ignoreColumns = ignoreColumns;
            return self();
        }

        public T containColumns(List<String> containColumns) {
            this.containColumns = containColumns;
            return self();
        }

        public T moduleStr(String moduleStr) {
            this.moduleStr = moduleStr;
            return self();
        }
    }

    public Boolean getNoPaging() { return noPaging; }
    public Long getPageSize() { return pageSize; }
    public Long getCurrent() { return current; }
    public Boolean getDetail() { return detail; }
    public Boolean getEnableSort() { return enableSort; }
    public List<BaseSort> getSorts() { return sorts; }
    public String getSortsStr() { return sortsStr; }
    public List<String> getIgnoreColumns() { return ignoreColumns; }
    public List<String> getContainColumns() { return containColumns; }
    public String getModuleStr() { return moduleStr; }
}
