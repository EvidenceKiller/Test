package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class DictRequest extends BaseRequest {

    @SerializedName("dictId")
    private String dictId;

    private DictRequest(Builder builder) {
        super(builder);
        this.dictId = builder.dictId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        private String dictId;

        public Builder dictId(String dictId) {
            this.dictId = dictId;
            return this;
        }

        public DictRequest build() {
            return new DictRequest(this);
        }
    }
}