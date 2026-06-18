package com.magento.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_PATH = "src/test/resources/config/config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties from " + CONFIG_PATH, e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }

    public static String getBrowser() {
        return get("browser").toLowerCase();
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static String getAppUrl() {
        return get("app.url");
    }

    public static int getImplicitTimeout() {
        return Integer.parseInt(get("timeout.implicit"));
    }

    public static int getExplicitTimeout() {
        return Integer.parseInt(get("timeout.explicit"));
    }

    public static int getParallelScenarios() {
        return Integer.parseInt(get("parallel.scenarios"));
    }
}
