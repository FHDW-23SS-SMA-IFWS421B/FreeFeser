package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.ApiCredentials;

import java.util.function.Function;

public class YamlApiCredentials implements ApiCredentials {

    private final Credentials credentials;
    private final Function<Credentials, String> apiKeyExtractor;

    public YamlApiCredentials(Credentials credentials, Function<Credentials, String> apiKeyExtractor) {
        this.credentials = credentials;
        this.apiKeyExtractor = apiKeyExtractor;
    }

    @Override
    public String getApiKey() {
        return apiKeyExtractor.apply(credentials);
    }
}
