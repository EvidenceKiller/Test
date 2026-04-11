package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 场景项目
 * Path       : terminal/sportSku/v1/getSportSkuList
 */
@Entity(tableName = "_scene_sport")
public class SceneSportEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 应用编码
    @ColumnInfo(name = "_app_code")
    private String appCode;
    @ColumnInfo(name = "_app_codes")
    private List<String> appCodes;

    // 场景Id
    @ColumnInfo(name = "_scene_id")
    private String sceneId;

    // sku标识码
    @ColumnInfo(name = "_sport_sku_id")
    private String sportSkuId;

    // 运动性质代码逗号分割
    @ColumnInfo(name = "_sport_nature_codes")
    private String sportNatureCodes;

    // 运动性质名称
    @ColumnInfo(name = "_sport_nature_names")
    private String sportNatureNames;

    // 运动类型代码 参考字典编码：adl_sport_type
    @ColumnInfo(name = "_sport_type_code")
    private String sportTypeCode;

    // 运动类型名称
    @ColumnInfo(name = "_sport_type_name")
    private String sportTypeName;

    // 运动单项代码 参考字典编码：adl_sport_item
    @ColumnInfo(name = "_sport_item_code")
    private String sportItemCode;

    // 运动单项名称
    @ColumnInfo(name = "_sport_item_name")
    private String sportItemName;

    // 运动sku名称
    @ColumnInfo(name = "_sport_sku_name")
    private String sportSkuName;

    // 测量方式 参考字典编码：adl_bms_sport_sku_measure_mode
    @ColumnInfo(name = "_measure_mode")
    private String measureMode;

    // 测量单位代码 参考字典编码：adl_sport_sport_unit
    @ColumnInfo(name = "_measure_unit_code")
    private String measureUnitCode;

    // 测量范围结束值
    @ColumnInfo(name = "_range_end_value")
    private String rangeEndValue;

    // 测量范围起始值
    @ColumnInfo(name = "_range_start_value")
    private String rangeStartValue;

    // 精度类型
    @ColumnInfo(name = "_accuracy_type")
    private String accuracyType;

    // 结束类型 参考字典编码：adl_bms_sport_sku_end_type
    @ColumnInfo(name = "_end_type")
    private String endType;

    // 精度方式
    @ColumnInfo(name = "_accuracy_method")
    private String accuracyMethod;

    // 结束单位代码 参考字典编码：adl_sport_end_unit
    @ColumnInfo(name = "_end_value")
    private String endValue;

    // 结束方式为其他时的备注
    @ColumnInfo(name = "_end_remark")
    private String endRemark;

    // 默认图标地址
    @ColumnInfo(name = "_icon_url")
    private String iconUrl;

    // 封面图片地址
    @ColumnInfo(name = "_cover_url")
    private String coverUrl;

    // 动作演示地址
    @ColumnInfo(name = "_action_video_rrl")
    private String actionVideoUrl;

    // 音频地址
    @ColumnInfo(name = "_audio_url")
    private String audioUrl;

    // 音频名称
    @ColumnInfo(name = "_audio_name")
    private String audioName;

    // 视频地址
    @ColumnInfo(name = "_video_url")
    private String videoUrl;

    // 视频封面地址
    @ColumnInfo(name = "_video_cover_url")
    private String videoCoverUrl;

    // 项目描述富文本
    @ColumnInfo(name = "_sport_sku_desc")
    private String sportSkuDesc;

    // 体测模板文件地址
    @ColumnInfo(name = "_test_template_url")
    private String testTemplateUrl;

    // 体测模板文件名称
    @ColumnInfo(name = "_test_template_name")
    private String testTemplateName;

    // 题库模板地址
    @ColumnInfo(name = "_qus_template_url")
    private String qusTemplateUrl;

    // 题库模板名称
    @ColumnInfo(name = "_qus_template_name")
    private String qusTemplateName;

    // 腾讯视频VID
    @ColumnInfo(name = "_tx_v_id")
    private String txVid;

    // 备注
    @ColumnInfo(name = "_remark")
    private String remark;

    // 启停用状态
    @ColumnInfo(name = "_enabled")
    private boolean enabled;

    // 项目高低优 参考字典编码：adl_bms_sport_priority
    @ColumnInfo(name = "_sport_priority")
    private String sportPriority;

    // 场景运动Id（按场景查询才有）
    @ColumnInfo(name = "_scene_sku_id")
    private String sceneSkuId;

    // 素质
    @ColumnInfo(name = "_qualities")
    private List<String> qualities;

    // 测试人数
    @ColumnInfo(name = "_test_count", defaultValue = "")
    private String testCount;

    @ColumnInfo(name = "_scene_sku_type", defaultValue = "")
    private String sceneSkuType;

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }

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

    public String getSportSkuName() {
        return sportSkuName;
    }

    public void setSportSkuName(String sportSkuName) {
        this.sportSkuName = sportSkuName;
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

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public String getAccuracyMethod() {
        return accuracyMethod;
    }

    public void setAccuracyMethod(String accuracyMethod) {
        this.accuracyMethod = accuracyMethod;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }

    public String getEndRemark() {
        return endRemark;
    }

    public void setEndRemark(String endRemark) {
        this.endRemark = endRemark;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public String getSportSkuDesc() {
        return sportSkuDesc;
    }

    public void setSportSkuDesc(String sportSkuDesc) {
        this.sportSkuDesc = sportSkuDesc;
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

    public String getTxVid() {
        return txVid;
    }

    public void setTxVid(String txVid) {
        this.txVid = txVid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSportPriority() {
        return sportPriority;
    }

    public void setSportPriority(String sportPriority) {
        this.sportPriority = sportPriority;
    }

    public String getSceneSkuId() {
        return sceneSkuId;
    }

    public void setSceneSkuId(String sceneSkuId) {
        this.sceneSkuId = sceneSkuId;
    }

    public List<String> getQualities() {
        return qualities;
    }

    public void setQualities(List<String> qualities) {
        this.qualities = qualities;
    }

    public String getTestCount() {
        return testCount;
    }

    public void setTestCount(String testCount) {
        this.testCount = testCount;
    }

    public String getSceneSkuType() {
        return sceneSkuType;
    }

    public void setSceneSkuType(String sceneSkuType) {
        this.sceneSkuType = sceneSkuType;
    }

    public List<String> getAppCodes() {
        return appCodes;
    }

    public void setAppCodes(List<String> appCodes) {
        this.appCodes = appCodes;
    }
}
