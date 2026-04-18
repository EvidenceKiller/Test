package com.adl.service.exception;

/**
 * 通用异常
 */
public class NzCommonException extends NzBaseException {

    private static final String CODE = "NZ_COMMON_EXCEPTION";

    public NzCommonException(String message) {
        super(CODE, message);
    }

    public NzCommonException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}

