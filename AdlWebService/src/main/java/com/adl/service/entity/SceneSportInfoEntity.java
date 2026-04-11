package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 场景项目详情
 * Path       :
 */
public class SceneSportInfoEntity implements Serializable {
    // 性质编码逗号分割
    private String sportNatureCodes;

    // 性质名称逗号分割
    private String sportNatureNames;

    // 运动类型代码
    private String sportTypeCode;

    // 运动类型名称
    private String sportTypeName;

    // 运动sku名称
    private String sportSkuName;

    // sku标识码
    private String sportSkuId;

    // 运动单项代码
    private String sportItemCode;

    // 运动单项名称
    private String sportItemName;

    // 备注
    private String remark;

    // 素质
    private List<String> qualities;

    // 测量方式
    private String measureMode;

    // 测量单位代码
    private String measureUnitCode;

    // 测量范围结束值
    private String rangeEndValue;

    // 测量范围起始值
    private String rangeStartValue;

    // 精度类型
    private String accuracyType;

    // 精度方式
    private String accuracyMethod;

    // 结束类型
    private String endType;

    // 结束单位代码
    private String endValue;

    // 题库模板地址
    private String qusTemplateUrl;

    // 题库模板名称
    private String qusTemplateName;

    // 体测模板文件地址
    private String testTemplateUrl;

    // 体测模板文件名称
    private String testTemplateName;

    // 应用编码
    private List<String> appCodes;

    // 封面图片地址
    private String coverUrl;

    // 项目高低优
    private String sportPriority;

    // 默认图标地址
    private String iconUrl;

    // 视频地址
    private String videoUrl;

    // 视频封面地址
    private String videoCoverUrl;

    // 动作演示地址
    private String actionVideoUrl;

    // 音频地址
    private String audioUrl;

    // 音频名称
    private String audioName;

    // 腾讯视频VID
    private String txVid;

    // 运动规则描述，需要转为json传输
    private String sportSkuDesc;

    //  运动描述
    private List<SportSkuDescJsonEntity> sportSkuDescJson;

    // 测试人数
    private String testCount;

    public String getSportNatureCodes() {
        return sportNatureCodes;
    }

    public void setSportNatureCodes(String sportNatureCodes) {
        this.sportNatureCodes = sportNatureCodes;
    }

    public String getSportNatureNames() {
        return sportNatureNames;
    }

    public void setSportNatureNames(String sportNatureNames) {
        this.sportNatureNames = sportNatureNames;
    }

    public String getSportTypeCode() {
        return sportTypeCode;
    }

    public void setSportTypeCode(String sportTypeCode) {
        this.sportTypeCode = sportTypeCode;
    }

    public String getSportTypeName() {
        return sportTypeName;
    }

    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }

    public String getSportSkuName() {
        return sportSkuName;
    }

    public void setSportSkuName(String sportSkuName) {
        this.sportSkuName = sportSkuName;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

    public String getSportItemCode() {
        return sportItemCode;
    }

    public void setSportItemCode(String sportItemCode) {
        this.sportItemCode = sportItemCode;
    }

    public String getSportItemName() {
        return sportItemName;
    }

    public void setSportItemName(String sportItemName) {
        this.sportItemName = sportItemName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getQualities() {
        return qualities;
    }

    public void setQualities(List<String> qualities) {
        this.qualities = qualities;
    }

    public String getMeasureMode() {
        return measureMode;
    }

    public void setMeasureMode(String measureMode) {
        this.measureMode = measureMode;
    }

    public String getMeasureUnitCode() {
        return measureUnitCode;
    }

    public void setMeasureUnitCode(String measureUnitCode) {
        this.measureUnitCode = measureUnitCode;
    }

    public String getRangeEndValue() {
        return rangeEndValue;
    }

    public void setRangeEndValue(String rangeEndValue) {
        this.rangeEndValue = rangeEndValue;
    }

    public String getRangeStartValue() {
        return rangeStartValue;
    }

    public void setRangeStartValue(String rangeStartValue) {
        this.rangeStartValue = rangeStartValue;
    }

    public String getAccuracyType() {
        return accuracyType;
    }

    public void setAccuracyType(String accuracyType) {
        this.accuracyType = accuracyType;
    }

    public String getAccuracyMethod() {
        return accuracyMethod;
    }

    public void setAccuracyMethod(String accuracyMethod) {
        this.accuracyMethod = accuracyMethod;
    }

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }

    public String getQusTemplateUrl() {
        return qusTemplateUrl;
    }

    public void setQusTemplateUrl(String qusTemplateUrl) {
        this.qusTemplateUrl = qusTemplateUrl;
    }

    public String getQusTemplateName() {
        return qusTemplateName;
    }

    public void setQusTemplateName(String qusTemplateName) {
        this.qusTemplateName = qusTemplateName;
    }

    public String getTestTemplateUrl() {
        return testTemplateUrl;
    }

    public void setTestTemplateUrl(String testTemplateUrl) {
        this.testTemplateUrl = testTemplateUrl;
    }

    public String getTestTemplateName() {
        return testTemplateName;
    }

    public void setTestTemplateName(String testTemplateName) {
        this.testTemplateName = testTemplateName;
    }

    public List<String> getAppCodes() {
        return appCodes;
    }

    public void setAppCodes(List<String> appCodes) {
        this.appCodes = appCodes;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getSportPriority() {
        return sportPriority;
    }

    public void setSportPriority(String sportPriority) {
        this.sportPriority = sportPriority;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoCoverUrl() {
        return videoCoverUrl;
    }

    public void setVideoCoverUrl(String videoCoverUrl) {
        this.videoCoverUrl = videoCoverUrl;
    }

    public String getActionVideoUrl() {
        return actionVideoUrl;
    }

    public void setActionVideoUrl(String actionVideoUrl) {
        this.actionVideoUrl = actionVideoUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getTxVid() {
        return txVid;
    }

    public void setTxVid(String txVid) {
        this.txVid = txVid;
    }

    public String getSportSkuDesc() {
        return sportSkuDesc;
    }

    public void setSportSkuDesc(String sportSkuDesc) {
        this.sportSkuDesc = sportSkuDesc;
    }

    public List<SportSkuDescJsonEntity> getSportSkuDescJson() {
        return sportSkuDescJson;
    }

    public void setSportSkuDescJson(List<SportSkuDescJsonEntity> sportSkuDescJson) {
        this.sportSkuDescJson = sportSkuDescJson;
    }

    public String getTestCount() {
        return testCount;
    }

    public void setTestCount(String testCount) {
        this.testCount = testCount;
    }
}
