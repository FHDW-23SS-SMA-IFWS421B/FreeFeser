package de.fhdw.freefeser.api.util;

public interface JsonParser {

    <T> T fromJson(String json, Class<T> type);
}


