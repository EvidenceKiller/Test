package com.adl.service.local;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单 key-value 内存缓存：支持 TTL 过期判断。
 *
 * <p>约定：当 ttlMillis <= 0 时，视为永不过期。</p>
 */
public final class MemoryCacheManager<K, V> {

    public static final long NEVER_EXPIRE = 0;

    private static volatile MemoryCacheManager<?, ?> sInstance;

    private final Map<K, CacheItem<V>> cache = new ConcurrentHashMap<>();

    private MemoryCacheManager() {
    }

    @SuppressWarnings("unchecked")
    public static <K, V> MemoryCacheManager<K, V> getInstance() {
        MemoryCacheManager<?, ?> local = sInstance;
        if (local == null) {
            synchronized (MemoryCacheManager.class) {
                local = sInstance;
                if (local == null) {
                    local = new MemoryCacheManager<>();
                    sInstance = local;
                }
            }
        }
        return (MemoryCacheManager<K, V>) local;
    }

    public void put(K key, V value, long ttlMillis) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        long now = System.currentTimeMillis();
        long expireTime = ttlMillis <= 0 ? Long.MAX_VALUE : now + ttlMillis;
        cache.put(key, new CacheItem<>(value, now, expireTime));
    }

    /**
     * 获取缓存值：若已过期会返回 null，并将该 key 从缓存中移除。
     */
    public V get(K key) {
        if (key == null) {
            return null;
        }
        CacheItem<V> item = cache.get(key);
        if (item == null) {
            return null;
        }
        long now = System.currentTimeMillis();
        if (item.isExpired(now)) {
            cache.remove(key);
            return null;
        }
        return item.value;
    }

    /**
     * 判断 key 是否已过期；key 不存在时返回 false。
     */
    public boolean isExpired(K key) {
        if (key == null) {
            return false;
        }
        CacheItem<V> item = cache.get(key);
        if (item == null) {
            return false;
        }
        return item.isExpired(System.currentTimeMillis());
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    private static final class CacheItem<V> {
        private final V value;
        private final long timestampMillis;
        private final long expireTimeMillis;

        private CacheItem(V value, long timestampMillis, long expireTimeMillis) {
            this.value = value;
            this.timestampMillis = timestampMillis;
            this.expireTimeMillis = expireTimeMillis;
        }

        private boolean isExpired(long nowMillis) {
            return expireTimeMillis != Long.MAX_VALUE && nowMillis > expireTimeMillis;
        }
    }
}

