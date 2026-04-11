package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class WikiDetailEntity implements Serializable {

    private List<String> appCodes;
    private String createTime;
    private boolean enabled;
    private String wikiContext;
    private String wikiCover;
    private String wikiId;
    private String wikiName;
    private String wikiSort;
    private String wikiType;
    private List<String> wikiTypeIds;
    private List<WikiInfoVideoEntity> wikiVideo;

    public List<String> getAppCodes() {
        return appCodes;
    }

    public void setAppCodes(List<String> appCodes) {
        this.appCodes = appCodes;
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

    public String getWikiContext() {
        return wikiContext;
    }

    public void setWikiContext(String wikiContext) {
        this.wikiContext = wikiContext;
    }

    public String getWikiCover() {
        return wikiCover;
    }

    public void setWikiCover(String wikiCover) {
        this.wikiCover = wikiCover;
    }

    public String getWikiId() {
        return wikiId;
    }

    public void setWikiId(String wikiId) {
        this.wikiId = wikiId;
    }

    public String getWikiName() {
        return wikiName;
    }

    public void setWikiName(String wikiName) {
        this.wikiName = wikiName;
    }

    public String getWikiSort() {
        return wikiSort;
    }

    public void setWikiSort(String wikiSort) {
        this.wikiSort = wikiSort;
    }

    public String getWikiType() {
        return wikiType;
    }

    public void setWikiType(String wikiType) {
        this.wikiType = wikiType;
    }

    public List<String> getWikiTypeIds() {
        return wikiTypeIds;
    }

    public void setWikiTypeIds(List<String> wikiTypeIds) {
        this.wikiTypeIds = wikiTypeIds;
    }

    public List<WikiInfoVideoEntity> getWikiVideo() {
        return wikiVideo;
    }

    public void setWikiVideo(List<WikiInfoVideoEntity> wikiVideo) {
        this.wikiVideo = wikiVideo;
    }
}
