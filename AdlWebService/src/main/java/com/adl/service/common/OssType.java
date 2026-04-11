package com.adl.service.common;

public enum OssType {
    MINIO("minio"),
    QINIU("qiniu");

    private final String value;

    OssType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
