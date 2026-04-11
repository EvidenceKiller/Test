package com.adl.service.exception;

/**
 * 未知异常兜底。
 */
public final class NzEmptyResponseException extends NzBaseException {

    public static final String CODE = "NZ_EMPTY_RESPONSE_EXCEPTION";

    public NzEmptyResponseException(String message) {
        super(CODE, message);
    }

    public NzEmptyResponseException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}

