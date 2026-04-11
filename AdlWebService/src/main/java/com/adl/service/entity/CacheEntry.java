package com.adl.service.entity;

import java.util.List;
import java.util.Map;

public class CacheEntry {
    private final String data;
    private final long timestamp;
    private final long expireTime;
    private final Map<String, List<String>> headers;
    private final int statusCode;
    private final String url;

    public CacheEntry(String data, long timestamp, long expireTime,
                      Map<String, List<String>> headers, int statusCode, String url) {
        this.data = data;
        this.timestamp = timestamp;
        this.expireTime = expireTime;
        this.headers = headers;
        this.statusCode = statusCode;
        this.url = url;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }

    public long getAgeInSeconds() {
        return (System.currentTimeMillis() - timestamp) / 1000;
    }

    // Getters
    public String getData() { return data; }
    public long getTimestamp() { return timestamp; }
    public long getExpireTime() { return expireTime; }
    public Map<String, List<String>> getHeaders() { return headers; }
    public int getStatusCode() { return statusCode; }
    public String getUrl() { return url; }
}
