package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class TempGroupInfo implements Serializable {
    private List<TempGroupAccountInfo> groupAccountInfos;
    private String groupId;
    private String groupName;
    private String groupNumber;

    public List<TempGroupAccountInfo> getGroupAccountInfos() {
        return groupAccountInfos;
    }

    public void setGroupAccountInfos(List<TempGroupAccountInfo> groupAccountInfos) {
        this.groupAccountInfos = groupAccountInfos;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
} 