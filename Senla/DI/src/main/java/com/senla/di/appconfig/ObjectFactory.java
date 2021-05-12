package com.senla.di.appconfig;


import com.senla.di.appconfig.api.ObjectConfigurator;
import com.senla.di.exception.ConfigurationError;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger(ObjectFactory.class.getName());


    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        try {
            Set<Class> set = new HashSet<>(context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class));

            for (Class<? extends ObjectConfigurator> aClass : set) {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARN, "OBJECT_FACTORY_EXCEPTION", e);
        }
    }

    public <T> T createObject(Class<T> type) {
        try {
            T t = type.getDeclaredConstructor().newInstance();

            configurate(t, context);
            return t;

        } catch (Exception e) {
            LOGGER.log(Level.WARN, String.format("CREATE_OBJECT_EXCEPTION  %s", type), e);
        }
        throw new ConfigurationError(type.toString());

    }

    public void configurate(Object t, ApplicationContext context) {
        configurators.forEach((o1) -> {
            o1.configure(t, this.context);
        });
    }
}
