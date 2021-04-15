package com.senla.di.annotationhandlers;

import com.senla.di.annotations.ConfigProperty;
import com.senla.di.applicationConfigs.ApplicationContext;
import com.senla.di.applicationConfigs.ObjectConfigurator;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class ConfigPropertyHandler implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {

        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);

                if (annotation != null) {
                    setPropertiesMap(annotation.configName());

                    String value = annotation.propertyName().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.propertyName());

                    field.setAccessible(true);

                    Object variable = objectConvert(field, value);

                    field.set(t, variable);
                    field.setAccessible(false);
                }

            }
        }
    }

    private void setPropertiesMap(String path) {
        try {
            Properties properties = new Properties();
            propertiesMap = new HashMap<>();

            properties.load(new FileInputStream(path));

            Map<String, String> map = new HashMap<>();

            for (Object key : properties.keySet()) {
                propertiesMap.put((String) key, properties.getProperty((String) key));
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
