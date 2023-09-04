package de.fhdw.freefeser.app.util;

import com.google.gson.Gson;
import de.fhdw.freefeser.api.util.JsonParser;

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
