package com.adl.service.exception;

/**
 * 业务失败异常（BaseResponse.isSuccess()==false）。
 */
public final class NzEmptyDataException extends NzBaseException {

    private static final String CODE = "NZ_EMPTY_DATA_EXCEPTION";

    public NzEmptyDataException(String message) {
        super(CODE, message);
    }

    public NzEmptyDataException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}

