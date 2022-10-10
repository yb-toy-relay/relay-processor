package one.appscale.relayprocessor.infra.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/dev/local-cache")
@RestController
public class CaffeineCacheDevController {
    private final CacheManager cacheManager;
    private final String cacheMasterKey;

    public CaffeineCacheDevController(final CacheManager cacheManager,
                                      @Value("${app.cache.master-key}") final String cacheMasterKey) {
        this.cacheManager = cacheManager;
        this.cacheMasterKey = cacheMasterKey;
    }

    @GetMapping("/names")
    public Object getAllCacheNames() {
        return cacheManager.getCacheNames();
    }

    @GetMapping("/names/{name}/keys")
    public Object getAllKeysByName(@PathVariable("name") String name) {
        return getCaffeineCache(name).getNativeCache().asMap().keySet();
    }

    @GetMapping("/names/{name}/keys/{key}")
    public Object getCacheValueByKey(@PathVariable("name") String name,
                                     @PathVariable("key") String key) {
        return getCaffeineCache(name).getNativeCache().getIfPresent(key);
    }

    @DeleteMapping("/names/{name}/keys/{key}")
    public Object evictCacheByKey(@PathVariable("name") String name,
                                  @PathVariable("key") String key,
                                  @RequestHeader("Cache-Master-Key") final String cacheMasterKey) {
        if (!this.cacheMasterKey.equals(cacheMasterKey)) {
            throw new IllegalArgumentException("not allowed cache master key.");
        }
        final boolean isEvicted = getCaffeineCache(name).evictIfPresent(key);
        log.info("[dev] local cache is cleared. result:{}, cacheName:{}, key:{}", isEvicted, name, key);
        return isEvicted;
    }

    private CaffeineCache getCaffeineCache(final String name) {
        final Cache cache = cacheManager.getCache(name);
        if (cache == null) {
            throw new RuntimeException("Invalid cacheName:" + name);
        }
        return (CaffeineCache) cache;
    }
}
