package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.Serializer;

import com.google.gson.Gson;

public class GsonSerializer implements Serializer {

    private final Gson gson;

    public GsonSerializer() {
        this.gson = new Gson();
    }

    @Override
    public <T> String serialize(T object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T deserialize(String data, Class<T> clazz) {
        return gson.fromJson(data, clazz);
    }
}

