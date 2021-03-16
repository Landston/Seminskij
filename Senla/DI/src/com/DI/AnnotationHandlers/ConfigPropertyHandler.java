package com.DI.AnnotationHandlers;

import com.DI.Annotations.ConfigProperty;
import com.DI.ApplicationConfigs.ApplicationContext;
import com.DI.ApplicationConfigs.ObjectConfigurator;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigPropertyHandler implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    @SneakyThrows
    public ConfigPropertyHandler() {
        String path = "src/com/Models/resources/configuration.properties";
        Properties properties = new Properties();
        propertiesMap = new HashMap<>();

        properties.load(new FileInputStream(path));

        Map<String, String> map = new HashMap<>();

        for (Object key : properties.keySet()) {
            propertiesMap.put((String) key, properties.getProperty((String) key));
        }


    }

    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {

        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
                if (annotation != null) {
                    String value = annotation.propertyName().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.propertyName());
                    field.setAccessible(true);

                    field.set(t, Integer.valueOf(value));
                }

            }
        }
    }
}
