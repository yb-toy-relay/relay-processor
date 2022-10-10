package one.appscale.relayprocessor.domain.apikey;

import java.util.Set;

public record EventTokens(Set<String> eventTokens) {
    public static EventTokens of(final Set<String> eventTokens) {
        return new EventTokens(eventTokens);
    }

    public boolean hasToken(final String eventToken) {
        return this.eventTokens.contains(eventToken);
    }
}
