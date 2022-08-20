package one.appscale.relayprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RelayProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelayProcessorApplication.class, args);
    }

}
