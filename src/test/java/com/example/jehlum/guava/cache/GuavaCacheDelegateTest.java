package com.example.jehlum.guava.cache;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.lang.NonNull;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GuavaCacheDelegateTest {

    @Test
    void getInstance_Default() {
        // default cache
        GuavaCacheDelegate<Integer, String> cache = new GuavaCacheDelegate<>();
        cache.getInstance().put(10, "V1");
        cache.getInstance().put(20, "V2");
        assertEquals("V1", cache.getInstance().getIfPresent(10));
        assertEquals("V2", cache.getInstance().getIfPresent(20));
    }

    @Test
    void getInstance_LoadingCache() {
        // loading caching
        CacheLoader<Integer, String> cacheLoader = new CacheLoader<Integer, String>() {
            private final Map<Integer, String> map = ImmutableMap.of(1001, "V1001", 1002, "V1002");

            @Override
            @NonNull
            public String load(@NonNull Integer key) {
                return map.get(key);
            }
        };
        GuavaCacheDelegate<Integer, String> cache = new GuavaCacheDelegate<>(GuavaCacheDelegate.CacheType.LOADING_CACHE,
                100, 1000, cacheLoader);
        cache.getInstance().put(100, "V0");
        cache.getInstance().put(101, "V1");
        assertEquals("V0", cache.getInstance().getIfPresent(100));
        assertEquals("V1", ((LoadingCache<Integer, String>) cache.getInstance()).getUnchecked(101));
        // from cache loader
        assertEquals("V1002", ((LoadingCache<Integer, String>) cache.getInstance()).getUnchecked(1002));
    }

    @Test
    void getInstance_LoadingCacheException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new GuavaCacheDelegate<>(GuavaCacheDelegate.CacheType.LOADING_CACHE, 100, 1000, null));
        assertEquals("CacheLoader is required for LoadingCache type", exception.getMessage());
    }
}