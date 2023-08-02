package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.Serializer;

import org.yaml.snakeyaml.Yaml;

public class YamlSerializer implements Serializer {

    private final Yaml yaml;

    public YamlSerializer() {
        this.yaml = new Yaml();
    }

    @Override
    public <T> String serialize(T object) {
        return yaml.dump(object);
    }

    @Override
    public <T> T deserialize(String data, Class<T> clazz) {
        return yaml.loadAs(data, clazz);
    }
}

