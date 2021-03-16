package com.DI.ApplicationConfigs;

import com.DI.Annotations.ConfigProperty;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

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
