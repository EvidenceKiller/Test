package com.adl.service.http.cache;

public interface CacheEventListener {
    void onCacheHit(String requestUrl, long cacheAge);
    void onCacheMiss(String requestUrl);
    void onCacheSaved(String requestUrl, long size);
    void onCacheError(String requestUrl, String error);
}
