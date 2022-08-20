package one.appscale.relayprocessor.infra.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaProperties kafkaProperties;

    @Value("${spring.cloud.stream.bindings.consumeCallbackEvent-in-0.group}")
    private String callbackEventBatchConsumer;

    @Value("${spring.cloud.stream.bindings.consumeCallbackInstall-in-0.group}")
    private String callbackInstallBatchConsumer;

    @Value("${spring.cloud.stream.bindings.consumeCallbackReattribution-in-0.group}")
    private String callbackReattributionBatchConsumer;

    @Bean
    public ListenerContainerCustomizer<AbstractMessageListenerContainer<?, ?>> listenerContainerCustomizer() {
        return (container, destinationName, group) -> {
            if (group.equalsIgnoreCase(callbackEventBatchConsumer)) {
                customizeCallbackEvent(container);
            }

            if (group.equalsIgnoreCase(callbackInstallBatchConsumer)) {
                customizeCallbackInstall(container);
            }

            if (group.equalsIgnoreCase(callbackReattributionBatchConsumer)) {
                customizeCallbackReattribution(container);
            }
        };
    }

    private void customizeCallbackEvent(final AbstractMessageListenerContainer<?, ?> container) {
        container.setCommonErrorHandler(new CommonLoggingErrorHandler());
        container.getContainerProperties().setIdleBetweenPolls(kafkaProperties.callbackEvent().idleBetweenPolls());
    }

    private void customizeCallbackInstall(final AbstractMessageListenerContainer<?, ?> container) {
        container.setCommonErrorHandler(new CommonLoggingErrorHandler());
        container.getContainerProperties().setIdleBetweenPolls(kafkaProperties.callbackInstall().idleBetweenPolls());
    }

    private void customizeCallbackReattribution(final AbstractMessageListenerContainer<?, ?> container) {
        container.setCommonErrorHandler(new CommonLoggingErrorHandler());
        container.getContainerProperties().setIdleBetweenPolls(kafkaProperties.callbackReattribution().idleBetweenPolls());
    }
}
