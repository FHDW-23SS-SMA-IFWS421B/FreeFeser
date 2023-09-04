package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.YamlParser;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class SnakeYamlParser implements YamlParser {

    private final Yaml yaml;

    public SnakeYamlParser() {
        this.yaml = new Yaml();
    }

    @Override
    public <T> T load(InputStream inputStream, Class<T> type) {
        try {
            return yaml.loadAs(inputStream, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
