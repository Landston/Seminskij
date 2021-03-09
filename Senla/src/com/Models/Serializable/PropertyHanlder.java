package com.Models.Serializable;

import com.Models.exceptions.DAOException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyHanlder {

    private static Logger LOGGER = Logger.getLogger(PropertyHanlder.class.getName());
    private static final String PROPERTIES_FILE_PATH = "src/com/Models/resources/configuration.properties";
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
            LOGGER.log(Level.WARNING, "PROPERTY_LOAD_EXCEPTION", e);

        }
    }


}
