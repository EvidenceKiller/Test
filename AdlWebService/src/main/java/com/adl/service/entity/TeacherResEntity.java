package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class TeacherResEntity {
    /**
     * "records": [
     * {
     * "enableSort": false,
     * "moduleStr": "/tis/base/org/res",
     * "id": "1914513682173509634",
     * "orgId": "1905226587209015296",
     * "name": "水电费水电费是的",
     * "title": "水电费水电费是的",
     * "cover": "V2/busi/lessonAssistant/lessonResourceList/2025-04-22/1745290598596_fea30ca0.png",
     * "enabled": true,
     * "sortNum": 1,
     * "content": "<p>电风扇大幅度<img src=\"https://f.nezhasport.com/V2/busi/lessonAssistant/lessonResourceList/2025-04-22/1745290605670_b67fc99a.png\" alt=\"\" data-href=\"\" style=\"\"/></p>",
     * "remark": "",
     * "type": 2,
     * "createNum": "ABBY00001",
     * "createTime": 1745290607000,
     * "updateTime": 1745290607000
     * }
     * ],
     * "total": 1,
     * "size": 1,
     * "current": 1,
     * "pages": 1
     */
    private int total;
    private int size;
    private int current;
    private int pages;

    private List<Record> records;

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

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public static class Record implements Serializable {
        private boolean enableSort;
        private String moduleStr;
        private String id;
        private String orgId;
        private String name;
        private String title;
        private String cover;
        private boolean enabled;
        private int sortNum;
        private String content;
        private String remark;
        private int type;
        private String createNum;
        private long createTime;
        private long updateTime;

        public boolean isEnableSort() {
            return enableSort;
        }

        public void setEnableSort(boolean enableSort) {
            this.enableSort = enableSort;
        }

        public String getModuleStr() {
            return moduleStr;
        }

        public void setModuleStr(String moduleStr) {
            this.moduleStr = moduleStr;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getSortNum() {
            return sortNum;
        }

        public void setSortNum(int sortNum) {
            this.sortNum = sortNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateNum() {
            return createNum;
        }

        public void setCreateNum(String createNum) {
            this.createNum = createNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
