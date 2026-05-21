package com.saucedemo.utils;

import com.saucedemo.config.EnvLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ConfigReader {
    private static final Map<String, String> properties = new HashMap<>();

    static {
        try {
            EnvLoader.loadEnv();
            loadFromPropertiesFile();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Unable to initialize ConfigReader: " + e.getMessage());
        }
    }

    private ConfigReader() {
    }

    private static void loadFromPropertiesFile() throws IOException {
        Path configPath = Path.of(System.getProperty("user.dir"), "src", "test", "resources", "config", "config.properties");
        if (Files.exists(configPath)) {
            Properties props = new Properties();
            props.load(Files.newBufferedReader(configPath));
            props.forEach((key, value) -> properties.put(key.toString(), value.toString()));
        }
    }

    public static String get(String key) {
        return properties.getOrDefault(key, System.getProperty(key, System.getenv(key)));
    }

    public static String get(String key, String defaultValue) {
        return properties.getOrDefault(key, System.getProperty(key, System.getenv().getOrDefault(key, defaultValue)));
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Integer.parseInt(value.trim());
    }
}
