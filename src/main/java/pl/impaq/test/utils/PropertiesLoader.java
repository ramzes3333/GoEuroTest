package pl.impaq.test.utils;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by andrew on 28.09.16.
 */
public class PropertiesLoader {

    private final String propFileName = "config.properties";
    private Properties prop = new Properties();
    private boolean propertiesLoaded = false;

    private static PropertiesLoader instance = null;

    protected PropertiesLoader() {

    }

    public static PropertiesLoader getInstance() {
        if(instance == null) {
            instance = new PropertiesLoader();
        }
        return instance;
    }

    private void load() {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
            }
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public String getPropertyValue(String propertyName) {
        if(!propertiesLoaded) {
            load();
        }
        String propertyValue = prop.getProperty(propertyName);
        return propertyValue;
    }
}
