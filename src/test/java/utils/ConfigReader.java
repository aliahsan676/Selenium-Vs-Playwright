package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return get("url");
    }

    public static String getUsername() {
        return get("username");
    }

    public static String getPassword() {
        return get("password");
    }
}
