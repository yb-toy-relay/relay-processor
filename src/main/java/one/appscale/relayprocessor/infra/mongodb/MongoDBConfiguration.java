package one.appscale.relayprocessor.infra.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.WriteConcern;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.SocketSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoDBConfiguration {
    @Value("${app.mongodb.password}")
    private String password;

    @Bean
    public MongoClientSettings mongoClientSettings() {
        final MongoCredential credential = MongoCredential.createCredential("prod_relay_all",
                                                                            "relaydb",
                                                                            password.toCharArray());
        return MongoClientSettings.builder()
                                  .retryWrites(true)
                                  .applyToClusterSettings((ClusterSettings.Builder builder) -> {
                                      builder.srvHost("prod-relay.jyryu.mongodb.net")
                                          .srvServiceName("mongodb")
                                          .mode(ClusterConnectionMode.LOAD_BALANCED);
                                  })
                                  .credential(credential)
                                  .build();
    }

//    @Bean
//    public MongoClientSettings mongoClientSettings() {
//        return MongoClientSettings.builder()
//                                  .retryWrites(true)
//                                  .writeConcern(WriteConcern.MAJORITY)
//                                  .applyToSocketSettings((SocketSettings.Builder builder) -> {
//                                      builder.connectTimeout(10, TimeUnit.SECONDS)
//                                             .readTimeout(10, TimeUnit.SECONDS);
//                                  })
//                                  .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
//                                      builder.maxConnectionIdleTime(10, TimeUnit.SECONDS);
//                                  })
//                                  .build();
//    }

//    @Bean
//    public MongoClientSettings mongoClientSettings() {
//        return MongoClientSettings.builder()
//                                  .retryWrites(true)
//                                  .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
//                                      builder.maxSize(300) //connections count
//                                             .minSize(100)
//                                             .maxConnectionLifeTime(0, TimeUnit.MILLISECONDS)
//                                             .maxConnectionIdleTime(0, TimeUnit.MILLISECONDS)
//                                             .maxWaitTime(5000, TimeUnit.MILLISECONDS);
//                                  })
//                                  .applyToSocketSettings(builder -> {
//                                      builder.connectTimeout(5000, TimeUnit.MILLISECONDS);
//                                  })
//                                  .build();
//    }
}
