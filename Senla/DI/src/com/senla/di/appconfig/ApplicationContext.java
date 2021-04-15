package com.senla.di.appconfig;

import com.senla.di.annotation.Singleton;
import com.senla.di.appconfig.api.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationContext {

    private static ApplicationContext instance;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private ObjectFactory factory;
    private Config config;
    private static final Logger LOGGER = Logger.getLogger(ApplicationContext.class.getName());

    public static ApplicationContext getInstance(){
        if(instance == null) {
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
        Set<Class<?>> singletons = config.getScanner().getTypesAnnotatedWith(Singleton.class);

        LOGGER.log(Level.INFO, singletons.toString());
        singletons.stream()
                .filter(singleton -> !cache.containsValue(singleton))
                .forEach(singleton -> factory.createObject(singleton));
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
