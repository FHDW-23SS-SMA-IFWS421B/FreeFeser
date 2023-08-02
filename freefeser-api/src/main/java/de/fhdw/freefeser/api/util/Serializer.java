package de.fhdw.freefeser.api.util;

public interface Serializer {

    <T> String serialize(T object);
    <T> T deserialize(String data, Class<T> clazz);
}

