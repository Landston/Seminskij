package com.senla.di.appconfig;

import com.senla.di.annotation.Configuration;
import com.senla.di.annotation.Singleton;
import com.senla.di.appconfig.api.Config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.*;


public class ApplicationContext {

    private static ApplicationContext instance;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private ObjectFactory factory;
    private Config config;
    private static final Logger LOGGER = LogManager.getLogger(ApplicationContext.class.getName());


    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = Application.run("com.senla", new HashMap<>());
        }

        return instance;
    }

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public void init() {
        Set<Class<?>> configurations = config.getScanner().getTypesAnnotatedWith(Configuration.class);

        configurations.stream()
                .forEach(singleton -> {
                    this.getObject(singleton);
                });
    }

    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) return (T) cache.get(type);

        Class<T> impl = type;

        if (type.isInterface()) {
            impl = (Class<T>) config.getImplClass(type);
        }

        T t = factory.createObject(impl);

        if (impl.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }
        // maybe some other logic
        return t;

    }

    public ObjectFactory getFactory() {
        return this.factory;
    }

    public Config getConfig() {
        return this.config;
    }

}
