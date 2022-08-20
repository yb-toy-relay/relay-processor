package one.appscale.relayprocessor.infra.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("app.kafka")
public record KafkaProperties(CallbackEvent callbackEvent,
                              CallbackInstall callbackInstall,
                              CallbackReattribution callbackReattribution) {
    public record CallbackEvent(long idleBetweenPolls) {
    }

    public record CallbackInstall(long idleBetweenPolls) {
    }

    public record CallbackReattribution(long idleBetweenPolls) {
    }
}
