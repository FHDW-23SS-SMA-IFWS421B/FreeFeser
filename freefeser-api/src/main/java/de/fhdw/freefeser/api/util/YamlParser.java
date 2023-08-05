package de.fhdw.freefeser.api.util;

public interface YamlParser {

    <T> T load(String yamlContent, Class<T> type);
}

