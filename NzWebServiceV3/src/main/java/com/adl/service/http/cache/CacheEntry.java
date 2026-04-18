package com.adl.service.http.cache;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CacheEntry {
    private final String data;
    private final long timestamp;
    private final long expireTime;
    private final Map<String, List<String>> headers;
    private final int statusCode;
    private final String url;

    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }

    public long getAgeInSeconds() {
        return (System.currentTimeMillis() - timestamp) / 1000;
    }
}
