package com.adl.service.db.entity;

import com.adl.service.data.SportSkuData;
import com.adl.service.db.converter.ListSportSkuDescEntityConverter;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import lombok.Data;

@Data
@Entity(tableName = "_sport_sku")
@TypeConverters(value = {ListSportSkuDescEntityConverter.class})
public final class SportSkuEntity {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    @ColumnInfo(name = "_app_code")
    private String appCode;

    @ColumnInfo(name = "_scene_id")
    private String sceneId;

    @ColumnInfo(name = "_enable_sort")
    private Boolean enableSort;

    @ColumnInfo(name = "_module_str")
    private String moduleStr;

    /**
     * 精度方式 详见码表
     */
    @ColumnInfo(name = "_accuracy_method")
    private Integer accuracyMethod;

    /**
     * 精度类型 详见码表
     */
    @ColumnInfo(name = "_accuracy_type")
    private Integer accuracyType;

    /**
     * 动作演示地址
     */
    @ColumnInfo(name = "_action_video_url")
    private String actionVideoUrl;

    /**
     * 音频
     */
    @ColumnInfo(name = "_app_codes")
    private List<String> appCodes;

    /**
     * 音频地址
     */
    @ColumnInfo(name = "_audio_name")
    private String audioName;

    /**
     * 音频地址
     */
    @ColumnInfo(name = "_audio_url")
    private String audioUrl;

    /**
     * 封面图片地址
     */
    @ColumnInfo(name = "_cover_url")
    private String coverUrl;

    /**
     * 删除标记为
     */
    @ColumnInfo(name = "_del_flag")
    private Boolean delFlag;

    /**
     * 启停用 参考字典编码：adl_bms_enabled (1 启用 0 禁用)
     */
    @ColumnInfo(name = "_enabled")
    private Boolean enabled;

    /**
     * 结束方式为其他时的备注
     */
    @ColumnInfo(name = "_end_remark")
    private String endRemark;

    /**
     * 结束类型 参考字典编码：adl_bms_sport_sku_end_type
     */
    @ColumnInfo(name = "_end_type")
    private Integer endType;

    /**
     * 结束方式 参考字典编码：adl_sport_end_unit
     */
    @ColumnInfo(name = "_end_value")
    private Integer endValue;

    /**
     * 默认图标地址
     */
    @ColumnInfo(name = "_icon_url")
    private String iconUrl;

    /**
     * 测量方式 参考字典编码：adl_bms_sport_sku_measure_mode
     */
    @ColumnInfo(name = "_measure_mode")
    private String measureMode;

    /**
     * 测量单位代码 参考字典编码：adl_sport_sport_unit
     */
    @ColumnInfo(name = "_measure_unit_code")
    private String measureUnitCode;

    /**
     * 题库模板名称
     */
    @ColumnInfo(name = "_qualities")
    private List<Integer> qualities;

    /**
     * 题库模板名称
     */
    @ColumnInfo(name = "_qus_template_name")
    private String qusTemplateName;

    /**
     * 题库模板地址
     */
    @ColumnInfo(name = "_qus_template_url")
    private String qusTemplateUrl;

    /**
     * 测量范围结束值
     */
    @ColumnInfo(name = "_range_end_value")
    private Double rangeEndValue;

    /**
     * 测量范围起始值
     */
    @ColumnInfo(name = "_range_start_value")
    private Double rangeStartValue;

    /**
     * 备注
     */
    @ColumnInfo(name = "_remark")
    private String remark;

    /**
     * 场景Code
     */
    @ColumnInfo(name = "_scene_code")
    private String sceneCode;

    /**
     * 场景运动Id
     */
    @ColumnInfo(name = "_scene_sku_id")
    private String sceneSkuId;

    /**
     * 场景运动排序
     */
    @ColumnInfo(name = "_scene_sku_sort")
    private Integer sceneSkuSort;

    /**
     * 运动单项代码 参考字典编码：adl_sport_item
     */
    @ColumnInfo(name = "_sport_item_code")
    private String sportItemCode;

    /**
     * 运动单项名称
     */
    @ColumnInfo(name = "_sport_item_name")
    private String sportItemName;

    /**
     * 运动性质代码逗号分割
     */
    @ColumnInfo(name = "_sport_nature_codes")
    private String sportNatureCodes;

    /**
     * 运动性质名称
     */
    @ColumnInfo(name = "_sport_nature_names")
    private String sportNatureNames;

    /**
     * 项目高低优 参考字典编码：adl_bms_sport_priority
     */
    @ColumnInfo(name = "_sport_priority")
    private Integer sportPriority;

    /**
     * sku描述，是个json string
     */
    @ColumnInfo(name = "_sport_sku_desc")
    private String sportSkuDesc;

    /**
     * sku描述列表
     */
    @ColumnInfo(name = "_sport_sku_desc_json")
    private List<SportSkuDescEntity> sportSkuDescJson;

    /**
     * 运动类型代码 参考字典编码：adl_sport_type
     */
    @ColumnInfo(name = "_sport_sku_id")
    private String sportSkuId;

    /**
     * 运动类型代码 参考字典编码：adl_sport_type
     */
    @ColumnInfo(name = "_sport_sku_name")
    private String sportSkuName;

    /**
     * 运动类型代码 参考字典编码：adl_sport_type
     */
    @ColumnInfo(name = "_sport_type_code")
    private String sportTypeCode;

    /**
     * 运动类型名称
     */
    @ColumnInfo(name = "_sport_type_name")
    private String sportTypeName;

    /**
     * 测试人数 空为不限制
     */
    @ColumnInfo(name = "_test_count")
    private Integer testCount;

    /**
     * 测试人数
     */
    @ColumnInfo(name = "_test_template_name")
    private String testTemplateName;

    /**
     * 体测模板文件地址
     */
    @ColumnInfo(name = "_test_template_url")
    private String testTemplateUrl;

    /**
     * 腾讯视频VID
     */
    @ColumnInfo(name = "_tx_vid")
    private String txVid;

    /**
     * 视频封面地址
     */
    @ColumnInfo(name = "_video_cover_url")
    private String videoCoverUrl;

    /**
     * 视频地址
     */
    @ColumnInfo(name = "_video_url")
    private String videoUrl;

    /**
     * 场景SKU分类
     */
    @ColumnInfo(name = "_scene_sku_type")
    private String sceneSkuType;

    @ColumnInfo(name = "_create_time")
    private Long createTime;

    @ColumnInfo(name = "_update_time")
    private Long updateTime;

    public static SportSkuEntity convertToEntity(SportSkuData data, String appCode, String sceneId) {
        if (data == null) {
            return null;
        }
        SportSkuEntity entity = new SportSkuEntity();
        entity.setAppCode(appCode);
        entity.setSceneId(sceneId);
        entity.setAccuracyMethod(data.getAccuracyMethod());
        entity.setAccuracyType(data.getAccuracyType());
        entity.setActionVideoUrl(data.getActionVideoUrl());
        entity.setAppCodes(data.getAppCodes());
        entity.setAudioName(data.getAudioName());
        entity.setAudioUrl(data.getAudioUrl());
        entity.setCoverUrl(data.getCoverUrl());
        entity.setDelFlag(data.getDelFlag());
        entity.setEnabled(data.getEnabled());
        entity.setEndRemark(data.getEndRemark());
        entity.setEndType(data.getEndType());
        entity.setEndValue(data.getEndValue());
        entity.setIconUrl(data.getIconUrl());
        entity.setMeasureMode(data.getMeasureMode());
        entity.setMeasureUnitCode(data.getMeasureUnitCode());
        entity.setQualities(data.getQualities());
        entity.setQusTemplateName(data.getQusTemplateName());
        entity.setQusTemplateUrl(data.getQusTemplateUrl());
        entity.setRangeEndValue(data.getRangeEndValue());
        entity.setRangeStartValue(data.getRangeStartValue());
        entity.setRemark(data.getRemark());
        entity.setSceneCode(data.getSceneCode());
        entity.setSceneSkuId(data.getSceneSkuId());
        entity.setSceneSkuSort(data.getSceneSkuSort());
        entity.setSportItemCode(data.getSportItemCode());
        entity.setSportItemName(data.getSportItemName());
        entity.setSportNatureCodes(data.getSportNatureCodes());
        entity.setSportNatureNames(data.getSportNatureNames());
        entity.setSportPriority(data.getSportPriority());
        entity.setSportSkuDesc(data.getSportSkuDesc());
        entity.setSportSkuId(data.getSportSkuId());
        entity.setSportSkuName(data.getSportSkuName());
        entity.setSportTypeCode(data.getSportTypeCode());
        entity.setSportTypeName(data.getSportTypeName());
        entity.setTestCount(data.getTestCount());
        entity.setTestTemplateName(data.getTestTemplateName());
        entity.setTestTemplateUrl(data.getTestTemplateUrl());
        entity.setTxVid(data.getTxVid());
        entity.setVideoCoverUrl(data.getVideoCoverUrl());
        entity.setVideoUrl(data.getVideoUrl());
        entity.setSceneSkuType(data.getSceneSkuType());
        return entity;
    }

    public static SportSkuData convertToData(SportSkuEntity entity) {
        if (entity == null) {
            return null;
        }
        SportSkuData data = new SportSkuData();
        data.setAccuracyMethod(entity.getAccuracyMethod());
        data.setAccuracyType(entity.getAccuracyType());
        data.setActionVideoUrl(entity.getActionVideoUrl());
        data.setAppCodes(entity.getAppCodes());
        data.setAudioName(entity.getAudioName());
        data.setAudioUrl(entity.getAudioUrl());
        data.setCoverUrl(entity.getCoverUrl());
        data.setDelFlag(entity.getDelFlag());
        data.setEnabled(entity.getEnabled());
        data.setEndRemark(entity.getEndRemark());
        data.setEndType(entity.getEndType());
        data.setEndValue(entity.getEndValue());
        data.setIconUrl(entity.getIconUrl());
        data.setMeasureMode(entity.getMeasureMode());
        data.setMeasureUnitCode(entity.getMeasureUnitCode());
        data.setQualities(entity.getQualities());
        data.setQusTemplateName(entity.getQusTemplateName());
        data.setQusTemplateUrl(entity.getQusTemplateUrl());
        data.setRangeEndValue(entity.getRangeEndValue());
        data.setRangeStartValue(entity.getRangeStartValue());
        data.setRemark(entity.getRemark());
        data.setSceneCode(entity.getSceneCode());
        data.setSceneSkuId(entity.getSceneSkuId());
        data.setSceneSkuSort(entity.getSceneSkuSort());
        data.setSportItemCode(entity.getSportItemCode());
        data.setSportItemName(entity.getSportItemName());
        data.setSportNatureCodes(entity.getSportNatureCodes());
        data.setSportNatureNames(entity.getSportNatureNames());
        data.setSportPriority(entity.getSportPriority());
        data.setSportSkuDesc(entity.getSportSkuDesc());
        data.setSportSkuId(entity.getSportSkuId());
        data.setSportSkuName(entity.getSportSkuName());
        data.setSportTypeCode(entity.getSportTypeCode());
        data.setSportTypeName(entity.getSportTypeName());
        data.setTestCount(entity.getTestCount());
        data.setTestTemplateName(entity.getTestTemplateName());
        data.setTestTemplateUrl(entity.getTestTemplateUrl());
        data.setTxVid(entity.getTxVid());
        data.setVideoCoverUrl(entity.getVideoCoverUrl());
        data.setVideoUrl(entity.getVideoUrl());
        data.setSceneSkuType(entity.getSceneSkuType());
        return data;
    }
}
