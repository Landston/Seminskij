package com.senla.di.applicationConfigs;

import lombok.SneakyThrows;

import java.util.*;

public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        Set<Class> set = new HashSet<>( context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class));
        for (Class<? extends ObjectConfigurator> aClass : set){
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        System.out.println(configurators);
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type)  {
        T t = type.getDeclaredConstructor().newInstance();

        configurate(t, context);

        return t;
    }

    public void configurate(Object t, ApplicationContext context){
        configurators.forEach((o1) -> {o1.configure(t, this.context); });
    }
}
