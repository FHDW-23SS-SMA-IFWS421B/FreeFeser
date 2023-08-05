package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.ApiCredentials;
import de.fhdw.freefeser.api.util.YamlParser;

import java.util.function.Function;

public class YamlApiCredentials implements ApiCredentials {

    private final YamlParser yamlParser;
    private final String filePath;
    private final Function<Credentials, String> apiKeyExtractor;

    public YamlApiCredentials(YamlParser yamlParser, String filePath, Function<Credentials, String> apiKeyExtractor) {
        this.yamlParser = yamlParser;
        this.filePath = filePath;
        this.apiKeyExtractor = apiKeyExtractor;
    }

    @Override
    public String getApiKey() {
        Credentials credentials = yamlParser.load(filePath, Credentials.class);
        return apiKeyExtractor.apply(credentials);
    }
}
