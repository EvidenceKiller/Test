package com.adl.service.exception;

/**
 * 业务失败异常（BaseResponse.isSuccess()==false）。
 */
public final class NzNetworkException extends NzBaseException {

    public static final String CODE = "NZ_NETWORK_EXCEPTION";

    private final int httpCode;

    public NzNetworkException(int httpCode, String message) {
        super(CODE, message);
        this.httpCode = httpCode;
    }

    public NzNetworkException(int httpCode, String message, Throwable cause) {
        super(CODE, message, cause);
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}

