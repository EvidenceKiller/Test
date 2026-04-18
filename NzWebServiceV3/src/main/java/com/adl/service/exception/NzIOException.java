package com.adl.service.exception;

/**
 * 网络/IO 类异常（例如超时、断网等）。
 */
public final class NzIOException extends NzBaseException {

    private static final String CODE = "NZ_IO_EXCEPTION";

    public NzIOException(String message) {
        super(CODE, message);
    }

    public NzIOException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}

