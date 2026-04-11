package com.adl.service.entity;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 视频集
 */
public class SportVideoEntity implements Serializable {

    //  视频相对地址
    private String videoUrl = "";

    //  视频封面图
    private String videoCover = "";

    //  视频说明
    private String videoDesc = "";

    public SportVideoEntity() {
    }

    public SportVideoEntity(String videoUrl, String videoCover, String videoDesc) {
        this.videoUrl = videoUrl;
        this.videoCover = videoCover;
        this.videoDesc = videoDesc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public SportVideoEntity toCopy() {
        SportVideoEntity entity = new SportVideoEntity();
        entity.setVideoUrl(this.videoUrl);
        entity.setVideoCover(this.videoCover);
        entity.setVideoDesc(this.videoDesc);
        return entity;
    }
}
