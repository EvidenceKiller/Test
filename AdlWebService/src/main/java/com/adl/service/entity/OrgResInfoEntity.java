package com.adl.service.entity;
import java.util.List;

public class OrgResInfoEntity {
    private String content;
    private String cover;
    private String createNum;
    private String createTime;
    private boolean enabled;
    private String id;
    private String name;
    private String orgId;
    private String remark;
    private int sortNum;
    private String title;
    private int type;
    private String updateTime;
    private List<String> appCodes;
    private int resType;
    private List<ResType> resTypes;
    private List<ResFile> resFiles;

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreateNum() {
        return createNum;
    }

    public void setCreateNum(String createNum) {
        this.createNum = createNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getAppCodes() {
        return appCodes;
    }

    public void setAppCodes(List<String> appCodes) {
        this.appCodes = appCodes;
    }

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public List<ResType> getResTypes() {
        return resTypes;
    }

    public void setResTypes(List<ResType> resTypes) {
        this.resTypes = resTypes;
    }

    public List<ResFile> getResFiles() {
        return resFiles;
    }

    public void setResFiles(List<ResFile> resFiles) {
        this.resFiles = resFiles;
    }

    @Override
    public String toString() {
        return "OrgResInfoEntity{" +
                "content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", createNum='" + createNum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", enabled=" + enabled +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", orgId='" + orgId + '\'' +
                ", remark='" + remark + '\'' +
                ", sortNum=" + sortNum +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", updateTime='" + updateTime + '\'' +
                ", appCodes=" + appCodes +
                ", resType=" + resType +
                ", resTypes=" + resTypes +
                ", resFiles=" + resFiles +
                '}';
    }

    // Inner class for ResType
    public static class ResType {
        private String createTime;
        private String id;
        private String orgId;
        private int resType;
        private int sort;
        private String typeName;
        private String updateTime;

        // Getters and Setters
        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public int getResType() {
            return resType;
        }

        public void setResType(int resType) {
            this.resType = resType;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "ResType{" +
                    "createTime='" + createTime + '\'' +
                    ", id='" + id + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", resType=" + resType +
                    ", sort=" + sort +
                    ", typeName='" + typeName + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    '}';
        }
    }

    // Inner class for ResFile
    public static class ResFile {
        private String id;
        private String resFileDesc;
        private String resFileName;
        private String resFilePath;
        private String resFileCover;
        private long resFileSize;
        private String resFileTitle;
        private int resFileType;
        private String resId;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResFileDesc() {
            return resFileDesc;
        }

        public void setResFileDesc(String resFileDesc) {
            this.resFileDesc = resFileDesc;
        }

        public String getResFileName() {
            return resFileName;
        }

        public void setResFileName(String resFileName) {
            this.resFileName = resFileName;
        }

        public String getResFilePath() {
            return resFilePath;
        }

        public void setResFilePath(String resFilePath) {
            this.resFilePath = resFilePath;
        }

        public String getResFileCover() {
            return resFileCover;
        }

        public void setResFileCover(String resFileCover) {
            this.resFileCover = resFileCover;
        }

        public long getResFileSize() {
            return resFileSize;
        }

        public void setResFileSize(long resFileSize) {
            this.resFileSize = resFileSize;
        }

        public String getResFileTitle() {
            return resFileTitle;
        }

        public void setResFileTitle(String resFileTitle) {
            this.resFileTitle = resFileTitle;
        }

        public int getResFileType() {
            return resFileType;
        }

        public void setResFileType(int resFileType) {
            this.resFileType = resFileType;
        }

        public String getResId() {
            return resId;
        }

        public void setResId(String resId) {
            this.resId = resId;
        }

        @Override
        public String toString() {
            return "ResFile{" +
                    "id='" + id + '\'' +
                    ", resFileDesc='" + resFileDesc + '\'' +
                    ", resFileName='" + resFileName + '\'' +
                    ", resFilePath='" + resFilePath + '\'' +
                    ", resFileSize=" + resFileSize +
                    ", resFileTitle='" + resFileTitle + '\'' +
                    ", resFileType=" + resFileType +
                    ", resId='" + resId + '\'' +
                    '}';
        }
    }
}

