package com.DI.AnnotationHandlers;

import com.DI.ApplicationConfigs.ApplicationContext;
import com.DI.ApplicationConfigs.ObjectConfigurator;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SingletonAnnotationHandler implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    @SneakyThrows
    public SingletonAnnotationHandler() {

    }

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {

    }


}
