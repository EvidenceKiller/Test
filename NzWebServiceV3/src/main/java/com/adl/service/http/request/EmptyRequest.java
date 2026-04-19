package com.adl.service.http.request;

import lombok.Getter;

@Getter
public final class EmptyRequest extends BaseRequest {

    private EmptyRequest(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder extends BaseRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        Builder() {}

        public EmptyRequest build() {
            return new EmptyRequest(this);
        }
    }
}