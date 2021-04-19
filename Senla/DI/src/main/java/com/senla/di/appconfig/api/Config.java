package com.senla.di.appconfig.api;

import org.reflections.Reflections;

import java.util.Map;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> tClass);

    Reflections getScanner();
     boolean hasInterFace(Object t);
}
