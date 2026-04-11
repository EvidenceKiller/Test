package com.adl.service.http.cache;

import com.adl.service.entity.CacheEntry;

public interface IHttpCache {

    /**
     * 保存缓存数据
     *
     * @param key
     * @param value
     */
    void save(String key, CacheEntry value);

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    CacheEntry get(String key);

    /**
     * 删除缓存数据
     *
     * @param key
     */
    void remove(String key);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 获取缓存大小
     *
     * @return
     */
    long getCacheSize();
}
