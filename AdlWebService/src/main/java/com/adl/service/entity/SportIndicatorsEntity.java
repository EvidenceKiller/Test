package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : viwenzhang
 * Date       : 2024/7/31
 * Describe   : 运动记录
 */
public class SportIndicatorsEntity<VALUE, REMARK> implements Serializable {

    //  指标code
    private String indicatorsCode;
    //  指标名称
    private String indicatorsName;
    //  指标值
    private VALUE indicatorsValue;
    //  指标描述
    private REMARK indicatorsRemark;
    //  指标单位
    private String indicatorsUnit;
    //  指标视频
    private List<SportVideoEntity> indicatorsVideos;
    //  指标图片
    private List<SportImageEntity> indicatorsImages;

    public String getIndicatorsCode() {
        return indicatorsCode;
    }

    public void setIndicatorsCode(String indicatorsCode) {
        this.indicatorsCode = indicatorsCode;
    }

    public String getIndicatorsName() {
        return indicatorsName;
    }

    public void setIndicatorsName(String indicatorsName) {
        this.indicatorsName = indicatorsName;
    }

    public VALUE getIndicatorsValue() {
        return indicatorsValue;
    }

    public void setIndicatorsValue(VALUE indicatorsValue) {
        this.indicatorsValue = indicatorsValue;
    }

    public REMARK getIndicatorsRemark() {
        return indicatorsRemark;
    }

    public void setIndicatorsRemark(REMARK indicatorsRemark) {
        this.indicatorsRemark = indicatorsRemark;
    }

    public String getIndicatorsUnit() {
        return indicatorsUnit;
    }

    public void setIndicatorsUnit(String indicatorsUnit) {
        this.indicatorsUnit = indicatorsUnit;
    }

    public List<SportVideoEntity> getIndicatorsVideos() {
        return indicatorsVideos;
    }

    public void setIndicatorsVideos(List<SportVideoEntity> indicatorsVideos) {
        this.indicatorsVideos = indicatorsVideos;
    }

    public List<SportImageEntity> getIndicatorsImages() {
        return indicatorsImages;
    }

    public void setIndicatorsImages(List<SportImageEntity> indicatorsImages) {
        this.indicatorsImages = indicatorsImages;
    }

    public SportIndicatorsEntity<VALUE, REMARK> toCopy() {
        SportIndicatorsEntity<VALUE, REMARK> entity = new SportIndicatorsEntity<>();
        entity.setIndicatorsCode(this.indicatorsCode);
        entity.setIndicatorsName(this.indicatorsName);
        entity.setIndicatorsValue(this.indicatorsValue);
        entity.setIndicatorsRemark(this.indicatorsRemark);
        entity.setIndicatorsUnit(this.indicatorsUnit);
        entity.setIndicatorsVideos(this.indicatorsVideos);
        entity.setIndicatorsImages(this.indicatorsImages);
        return entity;
    }
}
