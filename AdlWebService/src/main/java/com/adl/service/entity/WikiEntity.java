package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * "orgId": "bfbeccbf9e164da4a770068391af5c9a",
 * "wikiId": "59b201e5f565425b8d3dbd7b576992a4",
 * "wikiCover": "V2/oper/cyclopaedia/2024-11-05/1730776475859_8c794bb8.jpg",
 * "wikiName": "百科",
 * "wikiType": "1",
 * "enabled": true,
 * "wikiSort": "1",
 * "wikiTypeNames": [
 * "跳绳"
 * ],
 * "appCodes": [
 * "101101"
 * ],
 * "createTime": 1730776492000
 */
public class WikiEntity implements Serializable {

    private List<WikiInfoEntity> records;
    private int total;
    private int size;
    private int current;
    private int pages;

    public List<WikiInfoEntity> getRecords() {
        return records;
    }

    public void setRecords(List<WikiInfoEntity> records) {
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

    public static class WikiInfoEntity {
        private String orgId;
        private String wikiId;
        private String wikiCover;
        private String wikiName;
        private String wikiType;
        private boolean enabled;
        private String wikiSort;
        private List<String> wikiTypeNames;
        private List<String> appCodes;
        private long createTime;

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getWikiId() {
            return wikiId;
        }

        public void setWikiId(String wikiId) {
            this.wikiId = wikiId;
        }

        public String getWikiCover() {
            return wikiCover;
        }

        public void setWikiCover(String wikiCover) {
            this.wikiCover = wikiCover;
        }

        public String getWikiName() {
            return wikiName;
        }

        public void setWikiName(String wikiName) {
            this.wikiName = wikiName;
        }

        public String getWikiType() {
            return wikiType;
        }

        public void setWikiType(String wikiType) {
            this.wikiType = wikiType;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getWikiSort() {
            return wikiSort;
        }

        public void setWikiSort(String wikiSort) {
            this.wikiSort = wikiSort;
        }

        public List<String> getWikiTypeNames() {
            return wikiTypeNames;
        }

        public void setWikiTypeNames(List<String> wikiTypeNames) {
            this.wikiTypeNames = wikiTypeNames;
        }

        public List<String> getAppCodes() {
            return appCodes;
        }

        public void setAppCodes(List<String> appCodes) {
            this.appCodes = appCodes;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}

