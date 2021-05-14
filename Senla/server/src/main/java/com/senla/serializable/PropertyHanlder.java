package com.senla.serializable;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;



public class PropertyHanlder {

    private static Logger LOGGER = LogManager.getLogger(PropertyHanlder.class.getName());
    private static final String PROPERTIES_FILE_PATH = "D:\\Senla\\proj\\senla\\server\\src\\main\\java\\com\\senla\\model\\resource\\configProperty.properties";
    private static Properties properties;

    public static Optional<String> getProperties(String key)  {
        if(properties == null){
            loadProperties();
        }
        return Optional.ofNullable(properties.getProperty(key));
    }

    private static void loadProperties()  {
        try(InputStream inputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.WARN, "PROPERTY_LOAD_EXCEPTION", e);

        }
    }


}
