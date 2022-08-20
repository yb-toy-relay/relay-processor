package one.appscale.relayprocessor.domain.callback.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayschema.domain.event.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("callback-event-consumer")
public class CallbackEventConsumer {
    private final EventMongoRepository repository;

    @Bean
    public Consumer<List<Event>> consumeCallbackEvent() {
        return eventList -> {
            log.info("received events: {}", eventList.size());
            repository.saveAll(eventList);
        };
    }
}
