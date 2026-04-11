package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 场景
 * Path       : /terminal/scene/v1/getSceneList
 */
@Entity(tableName = "_scene")
public class SceneEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    // 应用编码
    @ColumnInfo(name = "_app_code")
    private String appCode;

    // 场景ID
    @ColumnInfo(name = "_id")
    private String id;

    // 场景名称
    @ColumnInfo(name = "_scene_name")
    private String sceneName;

    // 场景类型（保留位，暂时用）
    @ColumnInfo(name = "_scene_type")
    private String sceneType;

    // true 启用 false 停用
    @ColumnInfo(name = "_enabled")
    private boolean enabled;

    // 场景类型（保留位，暂时用）
    @ColumnInfo(name = "_pid")
    private String pid;

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

    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
