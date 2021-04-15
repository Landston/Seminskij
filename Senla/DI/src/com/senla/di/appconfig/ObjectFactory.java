package com.senla.di.appconfig;

import com.senla.di.appconfig.api.ObjectConfigurator;
import com.senla.di.exception.ConfigurationError;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(ObjectFactory.class.getName());

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        Set<Class> set = new HashSet<>(context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class));

        for (Class<? extends ObjectConfigurator> aClass : set) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }


    public <T> T createObject(Class<T> type) {
        try {
            LOGGER.log(Level.INFO, String.format("Object type to create and configure + %s", type));
            T t = type.getDeclaredConstructor().newInstance();

            configurate(t, context);

            return t;
        } catch (InstantiationException e) {
            LOGGER.log(Level.WARNING, "Exception in reflection layer", e.getCause());
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "Exception in reflection layer", e.getCause().getClass());
        } catch (NoSuchMethodException e) {
            LOGGER.log(Level.WARNING, "Exception in reflection layer", e.getCause().getClass());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Exception in reflection layer", e.getCause().getClass());

        }
        throw new ConfigurationError(type.toString());

    }

    public void configurate(Object t, ApplicationContext context) {
        configurators.forEach((o1) -> {
            o1.configure(t, this.context);
        });
    }
}
