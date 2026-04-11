package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class ResTypeListRequest {

    @SerializedName("id")
    private String id;

    @SerializedName("wikiId")
    private String wikiId;

    @SerializedName("resType")
    private Integer resType;

    private ResTypeListRequest(Builder builder) {
        this.id = builder.id;
        this.wikiId = builder.wikiId;
        this.resType = builder.resType;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String id;
        private String wikiId;
        private Integer resType;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder wikiId(String wikiId) {
            this.wikiId = wikiId;
            return this;
        }

        public Builder resType(Integer resType) {
            this.resType = resType;
            return this;
        }

        public ResTypeListRequest build() {
            return new ResTypeListRequest(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getWikiId() {
        return wikiId;
    }

    public Integer getResType() {
        return resType;
    }
}