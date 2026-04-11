package com.adl.service.entity;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 图片集
 */
public class SportRemarkEntity implements Serializable {

    private String levelName = "";
    private String title = "";
    private String details = "";
    //  1:去程 2:返程
    private int route;
    private int violationStatus;
    private int longTimeIndex;

    public SportRemarkEntity() {
    }

    public SportRemarkEntity(String levelName, String title, String details) {
        this.levelName = levelName;
        this.title = title;
        this.details = details;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public int getViolationStatus() {
        return violationStatus;
    }

    public void setViolationStatus(int violationStatus) {
        this.violationStatus = violationStatus;
    }

    public int getLongTimeIndex() {
        return longTimeIndex;
    }

    public void setLongTimeIndex(int longTimeIndex) {
        this.longTimeIndex = longTimeIndex;
    }
}
