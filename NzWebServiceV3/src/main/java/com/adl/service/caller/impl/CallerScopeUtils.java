package com.adl.service.caller.impl;

import com.adl.service.web.request.RequestScope;

/**
 * Caller 层通用参数校验工具。
 */
final class CallerScopeUtils {

    private CallerScopeUtils() {
    }

    static void requireScope(RequestScope scope) {
        if (scope == null) {
            throw new IllegalArgumentException("scope must not be null (use RequestScope.of(activity|fragment))");
        }
    }
}
