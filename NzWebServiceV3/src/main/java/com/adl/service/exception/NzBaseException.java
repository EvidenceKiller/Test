package com.adl.service.exception;

/**
 * SDK 异常基类（简单框架：用于统一分类网络/服务端/分页/未知错误）。
 */
public abstract class NzBaseException extends Exception {

    private final String errorCode;

    protected NzBaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    protected NzBaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

