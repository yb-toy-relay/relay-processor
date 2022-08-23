package one.appscale.relayprocessor.infra.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoDBConfiguration {
    @Bean
    public MongoClientSettings mongoClientSettings() {
        return MongoClientSettings.builder()
                                  .retryWrites(true)
                                  .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
                                      builder.maxSize(300) //connections count
                                             .minSize(100)
                                             .maxConnectionLifeTime(0, TimeUnit.MILLISECONDS)
                                             .maxConnectionIdleTime(0, TimeUnit.MILLISECONDS)
                                             .maxWaitTime(5000, TimeUnit.MILLISECONDS);
                                  })
                                  .applyToSocketSettings(builder -> {
                                      builder.connectTimeout(5000, TimeUnit.MILLISECONDS);
                                  })
                                  .build();
    }
}
