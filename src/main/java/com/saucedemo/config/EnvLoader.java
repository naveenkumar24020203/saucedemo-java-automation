package com.saucedemo.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class EnvLoader {
    private EnvLoader() {
    }

    public static void loadEnv() throws IOException {
        Path envPath = Path.of(System.getProperty("user.dir"), ".env");
        if (Files.exists(envPath)) {
            Map<String, String> env = new HashMap<>();
            Files.lines(envPath)
                    .map(String::trim)
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .forEach(line -> {
                        int separator = line.indexOf('=');
                        if (separator > 0) {
                            String key = line.substring(0, separator).trim();
                            String value = line.substring(separator + 1).trim();
                            env.put(key, value);
                        }
                    });
            env.forEach((key, value) -> System.setProperty(key, value));
        }
    }
}
