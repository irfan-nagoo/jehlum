package com.example.jehlum.guava.cache;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author irfan.nagoo
 */
public class GuavaCacheDelegate<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaCacheDelegate.class);

    public enum CacheType {
        CACHE, LOADING_CACHE
    }

    private final CacheType cacheType;
    private final long maxSize;
    private final long timeOut;
    private final CacheLoader<K, V> cacheLoader;

    private Cache<K, V> cache;

    public GuavaCacheDelegate() {
        this(100, 1000);
    }

    public GuavaCacheDelegate(long maxSize, long timeOut) {
        this(CacheType.CACHE, maxSize, timeOut, null);
    }

    public GuavaCacheDelegate(CacheType cacheType, long maxSize, long timeOut, CacheLoader<K, V> cacheLoader) {
        this.cacheType = cacheType;
        this.cacheLoader = cacheLoader;
        this.maxSize = maxSize;
        this.timeOut = timeOut;
        init();
    }

    public Cache<K, V> getInstance() {
        return cache;
    }

    public void init() {
        switch (cacheType) {
            case LOADING_CACHE:
                initLoadingCache();
                break;
            case CACHE:
            default:
                initDefaultCache();
        }
    }

    private void initDefaultCache() {
        LOGGER.info("Initializing Default Cache instance");
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(timeOut, TimeUnit.MILLISECONDS)
                .build();
    }

    private void initLoadingCache() {
        LOGGER.info("Initializing Loading Cache instance");
        Preconditions.checkArgument(cacheLoader != null,
                "CacheLoader is required for LoadingCache type");
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(timeOut, TimeUnit.MILLISECONDS)
                .build(cacheLoader);
    }


}
