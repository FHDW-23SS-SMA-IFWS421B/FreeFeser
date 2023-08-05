package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.ApiCredentials;
import de.fhdw.freefeser.api.util.YamlParser;

public class YamlApiCredentials implements ApiCredentials {

    private final YamlParser yamlParser;
    private final String filePath;

    public YamlApiCredentials(YamlParser yamlParser, String filePath) {
        this.yamlParser = yamlParser;
        this.filePath = filePath;
    }

    @Override
    public String getApiKey() {
        Credentials credentials = yamlParser.load(filePath, Credentials.class);
        return credentials.getDeeplApiKey();
    }
}
