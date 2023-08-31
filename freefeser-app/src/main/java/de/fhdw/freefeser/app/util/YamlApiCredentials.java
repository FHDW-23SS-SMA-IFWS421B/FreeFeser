package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.ApiCredentials;
import de.fhdw.freefeser.api.util.YamlParser;

import java.io.InputStream;
import java.util.function.Function;

public class YamlApiCredentials implements ApiCredentials {

    private final YamlParser yamlParser;
    private final InputStream inputStream;
    private final Function<Credentials, String> apiKeyExtractor;

    public YamlApiCredentials(YamlParser yamlParser, InputStream inputStream, Function<Credentials, String> apiKeyExtractor) {
        this.yamlParser = yamlParser;
        this.inputStream = inputStream;
        this.apiKeyExtractor = apiKeyExtractor;
    }

    @Override
    public String getApiKey() {
        Credentials credentials = yamlParser.load(inputStream, Credentials.class);
        return apiKeyExtractor.apply(credentials);
    }
}
