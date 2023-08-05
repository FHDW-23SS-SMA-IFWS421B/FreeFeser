package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.YamlParser;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;

public class SnakeYamlParser implements YamlParser {

    private final Yaml yaml;

    public SnakeYamlParser() {
        this.yaml = new Yaml();
    }

    @Override
    public <T> T load(String filePath, Class<T> type) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            return yaml.loadAs(inputStream, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
