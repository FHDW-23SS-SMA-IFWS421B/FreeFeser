package de.fhdw.freefeser.api.util;

import java.io.InputStream;

public interface YamlParser {

    <T> T load(InputStream inputStream, Class<T> type);
}

