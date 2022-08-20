package one.appscale.relayprocessor.domain.callback.install;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayschema.domain.install.Install;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("callback-install-consumer")
public class CallbackInstallConsumer {
    private final InstallMongoRepository repository;

    @Bean
    public Consumer<List<Install>> consumeCallbackInstall() {
        return installList -> {
            log.info("received installs: {}", installList.size());
            repository.saveAll(installList);
        };
    }
}
