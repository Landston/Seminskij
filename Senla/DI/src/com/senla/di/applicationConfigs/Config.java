package com.senla.di.applicationConfigs;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> tClass);

    Reflections getScanner();
}
