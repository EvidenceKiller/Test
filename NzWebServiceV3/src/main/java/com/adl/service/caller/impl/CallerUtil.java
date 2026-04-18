package com.adl.service.caller.impl;

import com.adl.service.http.request.BasePageRequest;
import com.adl.service.http.request.RequestScope;

/**
 * Caller 层通用参数校验工具。
 */
final class CallerUtil {

    static void assertScope(RequestScope scope) {
        if (scope == null) {
            throw new IllegalArgumentException("scope must not be null (use RequestScope.of(activity|fragment))");
        }
    }

    static void assertCurrent(BasePageRequest request) {
        if (request.getCurrent() != null && request.getCurrent() != 1) {
            throw new IllegalArgumentException("get all pages must set current is 1");
        }
    }
}
