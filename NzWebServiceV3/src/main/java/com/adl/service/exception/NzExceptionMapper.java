package com.adl.service.exception;

import android.util.Log;

import com.adl.service.log.NzLog;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * 将可抛出的异常映射成 SDK 统一异常类型（简单框架）。
 */
public final class NzExceptionMapper {

    private NzExceptionMapper() {
    }

    public static NzBaseException map(Throwable throwable) {
        if (throwable == null) {
            return new NzUnknownException("unknown error", null);
        }
        if (throwable instanceof NzBaseException) {
            return (NzBaseException) throwable;
        }

        Throwable root = throwable;
        // 简化：不断往 cause 下找，尽量找到真实根因
        while (root.getCause() != null && root.getCause() != root) {
            root = root.getCause();
        }

        if (root instanceof IOException) {
            return new NzIOException(root.getMessage() != null ? root.getMessage() : "io error", root);
        }
        if (root instanceof HttpException) {
            HttpException he = (HttpException) root;
            String msg = he.message() != null ? he.message() : ("http error " + he.code());
            return new NzNetworkException(he.code(), msg, he);
        }

        String msg = root.getMessage() != null ? root.getMessage() : throwable.getMessage();
        return new NzUnknownException(msg != null ? msg : "unknown error", throwable);
    }

    public static String toMessage(Throwable throwable) {
        NzLog.i("NzExceptionMapper", "exception : " + throwable);
        NzBaseException ex = map(throwable);
        String msg = ex.getMessage();
        String safeMsg = throwable.getClass().getSimpleName() + ": " + (msg != null ? msg : ex.toString());
        if (ex.getErrorCode() == null || ex.getErrorCode().isEmpty()) {
            return safeMsg;
        }
        return "[" + ex.getErrorCode() + "] " + safeMsg;
    }
}

