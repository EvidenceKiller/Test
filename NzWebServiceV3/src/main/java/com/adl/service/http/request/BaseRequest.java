package com.adl.service.http.request;

import lombok.Getter;

/**
 * 请求基类
 */
@Getter
public abstract class BaseRequest {

    private transient Boolean forceUpdate;

    protected BaseRequest(Builder<?> builder) {
        this.forceUpdate = builder.forceUpdate;
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected Boolean forceUpdate = Boolean.FALSE;

        protected abstract T self();

        public T foreceUpdate(Boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
            return self();
        }
    }
}
