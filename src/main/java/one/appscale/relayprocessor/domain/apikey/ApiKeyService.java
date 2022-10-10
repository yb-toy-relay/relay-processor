package one.appscale.relayprocessor.domain.apikey;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Collections.emptySet;
import static one.appscale.relayprocessor.infra.cache.CaffeineCacheConfiguration.EVENT_TOKENS;

@RequiredArgsConstructor
@Service
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;

    @Cacheable(cacheNames = EVENT_TOKENS,
               sync = true,
               key = "'app-token:' + #p0")
    public EventTokens getEventTokens(final String appToken) {
        final Set<String> eventTokens = apiKeyRepository.findApiKeyDocumentByAppTokensContains(appToken)
                                                        .map(ApiKeyDocument::getEventTokens)
                                                        .orElse(emptySet());
        return EventTokens.of(eventTokens);
    }
}
