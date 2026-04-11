package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class DictsRequest {

    @SerializedName("dictId")
    private String dictId;

    private DictsRequest(Builder builder) {
        this.dictId = builder.dictId;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String dictId;

        public Builder dictId(String dictId) {
            this.dictId = dictId;
            return this;
        }

        public DictsRequest build() {
            return new DictsRequest(this);
        }
    }

    public String getDictId() {
        return dictId;
    }
}