package one.appscale.relayprocessor.domain.callback.reattribution;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayschema.domain.reattribution.Reattribution;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("callback-install-consumer")
public class CallbackReattributionConsumer {
    @Bean
    public Consumer<List<Reattribution>> consumeCallbackReattribution() {
        return reattributionList -> {
            log.info("received reattributions: {}", reattributionList.size());
            reattributionList.forEach(reattribution -> log.debug("received: {}", reattribution));
        };
    }
}
