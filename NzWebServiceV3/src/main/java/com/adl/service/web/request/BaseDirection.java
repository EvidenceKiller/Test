package com.adl.service.web.request;

public enum BaseDirection {
    ASC("ASC"),
    DESC("DESC");

    private String value;

    BaseDirection(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
