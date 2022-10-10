package one.appscale.relayprocessor.infra.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Arrays;

@EnableCaching
@Configuration
public class CaffeineCacheConfiguration extends CachingConfigurerSupport {
    public static final String EVENT_TOKENS = "event-tokens";
    private static final int DEFAULT_MAXIMUM_SIZE = 1000;

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandlerImpl();
    }

    @Bean
    public CacheManager caffeineCacheManager(@Value("${app.cache.event-tokens.duration}") Duration eventTokensDuration) {
        final var eventTokenCache = buildCache(EVENT_TOKENS, eventTokensDuration);
        return buildCacheManager(eventTokenCache);
    }

    private CaffeineCache buildCache(final String name, final Duration duration) {
        return buildCache(name, duration, DEFAULT_MAXIMUM_SIZE);
    }

    private CaffeineCache buildCache(final String name, final Duration duration, final int maximumSize) {
        return new CaffeineCache(name,
                                 Caffeine.newBuilder()
                                         .maximumSize(maximumSize)
                                         .expireAfterWrite(duration)
                                         .build());
    }

    private CacheManager buildCacheManager(CaffeineCache... caffeineCaches) {
        final SimpleCacheManager manager = new SimpleCacheManager();
        final var caches = Arrays.asList(caffeineCaches);
        manager.setCaches(caches);
        return manager;
    }
}
