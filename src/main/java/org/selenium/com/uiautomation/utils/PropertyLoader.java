package org.selenium.com.uiautomation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private static final String DEFAULT_FILE = "config.properties";

    // -Denv=qa -> config-qa.properties, falls back to config.properties when unset
    private static String resolveFileName() {
        String env = System.getProperty("env");
        return (env == null || env.isBlank()) ? DEFAULT_FILE : "config-" + env + ".properties";
    }

    private static Properties readProperties(String fileName) {
        Properties prop = new Properties();
        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException(fileName + " not found on classpath");
            }
            prop.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + fileName, e);
        }
        return prop;
    }

    private static String loadProperty(String fileName, String key) {

        String systemProperty = System.getProperty(key);

        if (systemProperty != null && !systemProperty.isBlank()) {

            return systemProperty;
        }
        Properties prop = readProperties(fileName);
        String value = prop.getProperty(key);

        if (value == null)
            throw new IllegalStateException("No property found for " + key + " under file " + fileName);

        return value;

    }

    public static String loadProperty(String key) {
        return loadProperty(resolveFileName(), key);
    }

}
