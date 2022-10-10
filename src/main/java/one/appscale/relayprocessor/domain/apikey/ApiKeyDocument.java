package one.appscale.relayprocessor.domain.apikey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "api_key")
@NoArgsConstructor(access = PROTECTED)
public class ApiKeyDocument {
    @EqualsAndHashCode.Include
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String apiKey;
    @Indexed(unique = true)
    private String owner;
    private Set<String> appTokens;
    private Set<String> eventTokens;
    private Set<String> emailDomains;
}
