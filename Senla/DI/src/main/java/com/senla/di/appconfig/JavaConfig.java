package com.senla.di.appconfig;

import com.senla.di.appconfig.api.Config;
import lombok.Getter;
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

    public boolean hasInterFace(Object t){
       return this.if2ImplClass.containsValue(t);
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }

    public Map<Class,Class> getIf2ImplClass(){
        return if2ImplClass;
    }
}
