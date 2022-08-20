package one.appscale.relayprocessor.infra.kafka.serdes;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.nio.charset.StandardCharsets;

/**
 * Deserialization 실패시 로깅만
 */
@Slf4j
public class ErrorLoggingDeserializer extends ErrorHandlingDeserializer<SpecificRecord> {

    public ErrorLoggingDeserializer() {
        super();
        setFailedDeserializationFunction(this::failedDeserializationFunction);
    }

    private SpecificRecord failedDeserializationFunction(final FailedDeserializationInfo failedInfo) {
        final String failedData = new String(failedInfo.getData(), StandardCharsets.UTF_8);
        final String topic = failedInfo.getTopic();
        log.warn("deserialize failed - topic: {}, message: {}, ", topic, failedData);
        return null;
    }
}