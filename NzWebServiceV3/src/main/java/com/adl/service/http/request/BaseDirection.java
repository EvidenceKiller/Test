package com.adl.service.http.request;

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
