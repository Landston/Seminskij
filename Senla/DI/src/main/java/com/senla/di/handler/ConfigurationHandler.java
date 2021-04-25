package com.senla.di.handler;

import com.senla.di.annotation.Configuration;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.di.appconfig.api.Config;
import com.senla.di.appconfig.api.ObjectConfigurator;
import com.senla.di.exception.ConfigurationError;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ConfigurationHandler implements ObjectConfigurator {

    private Logger LOGGER = LogManager.getLogger(ConfigurationHandler.class);
    @Override
    public void configure(Object t, ApplicationContext context) {

        try{
            for(Field filed : t.getClass().getFields()){

                if(filed.isAnnotationPresent(Configuration.class)){
                    filed.setAccessible(true);

                    Object newObject = context.getObject(filed.getType());

                    filed.set(t, newObject);
                }
            }
        } catch (IllegalAccessException e) {
           LOGGER.log(Level.WARN, String.format("Can con config object : %s ", t));
           throw new ConfigurationError(String.format("Confgir ERROR in : %s", t.getClass()), e);
        }


    }
}
