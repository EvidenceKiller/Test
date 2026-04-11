package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class TempGroupTeachEntity implements Serializable {
    private String classId;
    private List<TempGroupInfo> groups;
    private String ptPlanId;
    private String sceneCode;
    private String sportSkuId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public List<TempGroupInfo> getGroups() {
        return groups;
    }

    public void setGroups(List<TempGroupInfo> groups) {
        this.groups = groups;
    }

    public String getPtPlanId() {
        return ptPlanId;
    }

    public void setPtPlanId(String ptPlanId) {
        this.ptPlanId = ptPlanId;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getSportSkuId() {
        return sportSkuId;
    }

    public void setSportSkuId(String sportSkuId) {
        this.sportSkuId = sportSkuId;
    }
} 