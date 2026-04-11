package com.adl.service.entity;

import java.io.Serializable;

public class WikiInfoVideoEntity implements Serializable {

    private String id;
    //  百科标识码
    private String wikiId;
    //  视频封面地址
    private String wikiVideoCover;
    //  视频地址
    private String wikiVideoUrl;

    //运动百科视频描述
    private String wikiVideoDesc;

    //运动百科视频标题
    private String wikiVideoTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWikiId() {
        return wikiId;
    }

    public void setWikiId(String wikiId) {
        this.wikiId = wikiId;
    }

    public String getWikiVideoCover() {
        return wikiVideoCover;
    }

    public void setWikiVideoCover(String wikiVideoCover) {
        this.wikiVideoCover = wikiVideoCover;
    }

    public String getWikiVideoUrl() {
        return wikiVideoUrl;
    }

    public void setWikiVideoUrl(String wikiVideoUrl) {
        this.wikiVideoUrl = wikiVideoUrl;
    }

    public String getWikiVideoDesc() {
        return wikiVideoDesc;
    }

    public void setWikiVideoDesc(String wikiVideoDesc) {
        this.wikiVideoDesc = wikiVideoDesc;
    }

    public String getWikiVideoTitle() {
        return wikiVideoTitle;
    }

    public void setWikiVideoTitle(String wikiVideoTitle) {
        this.wikiVideoTitle = wikiVideoTitle;
    }
}
