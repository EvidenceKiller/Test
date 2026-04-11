package com.adl.service.entity;

import java.io.Serializable;

public class DictExcelEntity implements Serializable {

    private String dict;
    private String rightKey;
    private String wrongKey;
    private String type;

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getRightKey() {
        return rightKey;
    }

    public void setRightKey(String rightKey) {
        this.rightKey = rightKey;
    }

    public String getWrongKey() {
        return wrongKey;
    }

    public void setWrongKey(String wrongKey) {
        this.wrongKey = wrongKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
