package com.adl.service.entity;

/**
 * "image": {
 * *                             "imageDesc": "预摆帧",
 * *                             "imageUrl": "https://f.nezhasport.com//PTE/sport/indicator/images/1,2/0301/9cab286c-9406-4619-a756-d7f296128e3f_1741837084836.png"
 * *                         },
 * *                         "sportStatus": "未达标",
 * *                         "level": "2",
 * *                         "title": "您的预蹲后摆幅度不足",
 * *                         "remark": "预蹲时上
 */
public class IndicatorsValueEntity {
    private String sportStatus;
    private String level;
    private String title;
    private String remark;

    public String getSportStatus() {
        return sportStatus;
    }

    public void setSportStatus(String sportStatus) {
        this.sportStatus = sportStatus;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
