package org.selenium.com.weathershopper.utils;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Reads test data files (src/test/resources) into Java types - not for app/runtime config.
public class JsonReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static InputStream openStream(String fileName) {
        InputStream input = JsonReader.class.getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            throw new IllegalStateException(fileName + " not found on classpath");
        }
        return input;
    }

    public static <T> T readValue(String fileName, Class<T> type) {
        try (InputStream input = openStream(fileName)) {
            return MAPPER.readValue(input, type);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read " + fileName, e);
        }
    }

    public static <T> T readValue(String fileName, TypeReference<T> typeReference) {
        try (InputStream input = openStream(fileName)) {
            return MAPPER.readValue(input, typeReference);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read " + fileName, e);
        }
    }
}
