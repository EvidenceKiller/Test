package com.adl.service.web.request;

public final class WikiTypePageRequest extends BasePageRequest {

    private WikiTypePageRequest(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        public WikiTypePageRequest build() { return new WikiTypePageRequest(this); }
    }
}