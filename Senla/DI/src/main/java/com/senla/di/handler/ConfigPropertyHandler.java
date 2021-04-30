package com.senla.di.handler;

import com.senla.di.annotation.ConfigProperty;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.di.appconfig.api.ObjectConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;


public class ConfigPropertyHandler implements ObjectConfigurator {

    private static final Logger LOGGER = LogManager.getLogger(ConfigPropertyHandler.class.getName());
    private Map<String, String> propertyValueByKey;

    @Override
    public void configure(Object t, ApplicationContext context) {
        try {
            for (Field field : t.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(ConfigProperty.class)) {
                    ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);

                    if (annotation != null) {
                        setPropertyValueByKey(annotation.configName());

                        String value = annotation.propertyName().isEmpty() ? propertyValueByKey.get(field.getName()) : propertyValueByKey.get(annotation.propertyName());

                        field.setAccessible(true);

                        Object variable = objectConvert(field, value);

                        field.set(t, variable);
                        field.setAccessible(false);
                    }

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setPropertyValueByKey(String path) {
        try {
            Properties properties = new Properties();
            propertyValueByKey = new HashMap<>();

            properties.load(new FileInputStream(path));

            Map<String, String> map = new HashMap<>();

            for (Object key : properties.keySet()) {
                propertyValueByKey.put((String) key, properties.getProperty((String) key));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object objectConvert(Field field, String value) {
        String variable = field.getType().getSimpleName().toLowerCase();

        return switch (variable) {
            case "boolean" -> Boolean.valueOf(value);
            case "int", "integer" -> Integer.valueOf(value);
            case "float" -> Float.valueOf(value);
            case "double" -> Double.valueOf(value);
            case "long" -> Long.valueOf(value);
            case "char", "character" -> value.charAt(0);
            case "short" -> Short.valueOf(value);
            default -> value;
        };

    }

}
