//package one.appscale.relayprocessor.infra.mongodb;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoCredential;
//import com.mongodb.WriteConcern;
//import com.mongodb.connection.ClusterConnectionMode;
//import com.mongodb.connection.ClusterSettings;
//import com.mongodb.connection.ConnectionPoolSettings;
//import com.mongodb.connection.SocketSettings;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class MongoDBConfiguration {
//    @Bean
//    public MongoClientSettings mongoClientSettings() {
////        final MongoCredential credential = MongoCredential.createCredential("prod_relay_all",
////                                                                            "relaydb",
////                                                                            "password".toCharArray());
//        return MongoClientSettings.builder()
//                                  .retryWrites(true)
//                                  .writeConcern(WriteConcern.MAJORITY)
////                                  .credential(credential)
//                                  .applyToClusterSettings((ClusterSettings.Builder builder) -> {
//                                      builder.srvHost("prod-relay.jyryu.mongodb.net")
//                                          .srvServiceName("mongodb")
//                                          .mode(ClusterConnectionMode.LOAD_BALANCED);
//                                  })
//                                  .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
//                                      builder.maxConnectionIdleTime(10, TimeUnit.SECONDS);
//                                  })
//                                  .applyToSocketSettings((SocketSettings.Builder builder) -> {
//                                      builder.connectTimeout(10, TimeUnit.SECONDS)
//                                             .readTimeout(10, TimeUnit.SECONDS);
//                                  })
//                                  .build();
//    }
//}
