package de.xXSlideSlimeXx.main.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author xXSlideSlimeXx
 * @since 20.11.2023
 */
public final class Config {
    private final Properties properties;
    private final File file;

    public Config(String path) {
        this.file = new File(path);
        Properties properties;
        try {
                properties = new Properties();
            if(file.exists()) {
                properties.load(new FileInputStream(this.file));
            }
        } catch (IOException e) {
            e.printStackTrace();
            properties = null;
        }
        this.properties = properties;
    }

    public String getString(final String key) {
        return properties.getProperty(key);
    }

    public String getOrDefault(final String key, final String defaultValue) {
        return properties.containsKey(key) ? properties.getProperty(key) : defaultValue;
    }

    public String getOrDefault(final ConfigKey key) {
        return getOrDefault(key.getKey(), key.getDefaultValue());
    }
}
