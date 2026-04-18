package com.adl.service.exception;

import com.adl.service.callback.GetPageResult;

public final class NzGetPageException extends NzBaseException {
    private static final String CODE = "NZ_PAGE_REQUEST_EXCEPTION";

    private final GetPageResult result;

    public NzGetPageException(GetPageResult result) {
        super(CODE, result.getMessage());
        this.result = result;
    }

    public NzGetPageException(GetPageResult result, Throwable cause) {
        super(CODE, result.getMessage(), cause);
        this.result = result;
    }

    private GetPageResult getFailedPageNo() {
        return result;
    }
}
