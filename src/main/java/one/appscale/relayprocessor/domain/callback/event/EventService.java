package one.appscale.relayprocessor.domain.callback.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayprocessor.domain.apikey.ApiKeyService;
import one.appscale.relayprocessor.domain.apikey.EventTokens;
import one.appscale.relayschema.domain.event.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventService {
    private final EventMongoRepository repository;
    private final ApiKeyService apiKeyService;

    public List<Event> save(final List<Event> eventList) {
        final var filteredEvents = eventList
            .stream()
            .filter(this::hasEventToken)
            .toList();
        return repository.saveAll(filteredEvents);
    }

    private boolean hasEventToken(final Event event) {
        final String appToken = event.getAppInfo().getAppToken();
        final String eventToken = event.getEventInfo().getEvent();
        final EventTokens eventTokens = apiKeyService.getEventTokens(appToken);
        if (eventTokens.hasToken(eventToken)) {
            return true;
        }
        log.info("Drop unregistered event. appToken:{}, eventToken:{}", appToken, eventToken);
        return false;
    }
}
