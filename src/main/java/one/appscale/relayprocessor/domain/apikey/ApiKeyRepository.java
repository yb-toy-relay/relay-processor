package one.appscale.relayprocessor.domain.apikey;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApiKeyRepository extends MongoRepository<ApiKeyDocument, String> {
    Optional<ApiKeyDocument> findApiKeyDocumentByAppTokensContains(String appToken);
}
