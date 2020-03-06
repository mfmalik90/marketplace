package com.carousell.marketplace.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author faizanmalik
 * creation date 3/6/20
 */
@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {

    private static final String CATEGORIES = "categories";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(CATEGORIES);

        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {CATEGORIES})
    @Scheduled(fixedDelayString = "${cache-evict.ttl.categories}", initialDelay = 500)
    public void reportCacheEvict() {
    }
}
