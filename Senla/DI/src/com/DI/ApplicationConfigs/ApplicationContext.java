package com.DI.ApplicationConfigs;

import com.DI.Annotations.Singleton;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private ObjectFactory factory;
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    @SneakyThrows
    public <T> T getObject(Class<T> type){
        if(cache.containsKey(type)) return (T) cache.get(type);

        Class<T> impl = type;

        if(type.isInterface()){
         impl = (Class<T>) config.getImplClass(type);
        }

        T t = factory.createObject(impl);

        if(impl.isAnnotationPresent(Singleton.class)) cache.put(type, t);
        // maybe some other logic
        return t;

    }

    public Config getConfig() {
        return this.config;
    }
}
