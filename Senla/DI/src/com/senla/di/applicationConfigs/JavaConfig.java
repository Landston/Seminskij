package com.senla.di.applicationConfigs;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {

    private Reflections scanner;
    private Map<Class, Class> if2ImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> if2ImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.if2ImplClass = if2ImplClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> tClass) {
        return if2ImplClass.computeIfAbsent(tClass, aClass -> {
            Set<Class<? extends  T>> classes = scanner.getSubTypesOf(tClass);
            if(classes.size() != 1) {
                throw new RuntimeException(tClass + " zero or more than one implementation ");
            }
            return classes.iterator().next();
        });
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
