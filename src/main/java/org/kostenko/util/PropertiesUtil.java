package org.kostenko.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    static Properties prop = new Properties();

    static {
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("data.properties");
            prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static PropertiesUtil getInstance() {
        return new PropertiesUtil();
    }

    public Properties getProperties() {
        return prop;
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


}
