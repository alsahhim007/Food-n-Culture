package com.dalhousie.foodnculture.utilities;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {

    private static Properties properties;

    public void loadConfiguration(InputStream inputStream) throws Exception {
        properties = new Properties();
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("Unable to read property file application.properties");
        }
    }

    public static String getApiUrl() {
        return properties.getProperty("API_URL") + ":" + properties.getProperty("API_PORT");
    }
}
