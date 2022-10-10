package one.appscale.relayprocessor.infra.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

@Slf4j
public class CacheErrorHandlerImpl implements CacheErrorHandler {
    @Override
    public void handleCacheGetError(final RuntimeException e, final Cache cache, final Object key) {
        log.error("CacheGetError. key:{}, cache:{}, exception:{}", key, cache.getName(), e.getMessage());
    }

    @Override
    public void handleCachePutError(final RuntimeException e, final Cache cache, final Object key, final Object value) {
        log.error("CachePutError. key:{}, cache:{}, exception:{}", key, cache.getName(), e.getMessage());
    }

    @Override
    public void handleCacheEvictError(final RuntimeException e, final Cache cache, final Object key) {
        log.error("CacheEvictError. key:{}, cache:{}, exception:{}", key, cache.getName(), e.getMessage());
    }

    @Override
    public void handleCacheClearError(final RuntimeException e, final Cache cache) {
        log.error("CacheClearError. cache:{}, exception:{}", cache.getName(), e.getMessage());
    }
}
