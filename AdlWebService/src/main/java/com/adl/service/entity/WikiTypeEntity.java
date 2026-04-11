package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * "id": "85e5386325164211bc670242bda6ce16",
 * "typeName": "跳远",
 * "typeSort": "2",
 * "createTime": 1730776461000
 */

/**
 * "total": 2,
 * "size": 20,
 * "current": 1,
 * "pages": 1
 */
public class WikiTypeEntity implements Serializable {

    private List<WikiTypeInfo> records;
    private int total;
    private int size;
    private int current;
    private int pages;

    public List<WikiTypeInfo> getRecords() {
        return records;
    }

    public void setRecords(List<WikiTypeInfo> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public static class WikiTypeInfo {
        private String id;
        private String typeName;
        private String typeSort;
        private long createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeSort() {
            return typeSort;
        }

        public void setTypeSort(String typeSort) {
            this.typeSort = typeSort;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
