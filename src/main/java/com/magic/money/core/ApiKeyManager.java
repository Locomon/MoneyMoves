package com.magic.money.core;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiKeyManager {
    private static final String CONFIG_FILE = "apikeys.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ApiKeyManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IOException("API key config file not found: " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load API keys");
        }
    }

    public static String getKey(String provider) {
        return properties.getProperty(provider);
    }
}