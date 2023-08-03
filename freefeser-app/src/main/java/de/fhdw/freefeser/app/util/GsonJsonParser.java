package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.JsonParser;

import com.google.gson.Gson;

public class GsonJsonParser implements JsonParser {

    private final Gson gson;

    public GsonJsonParser() {
        this.gson = new Gson();
    }

    @Override
    public <T> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }
}
