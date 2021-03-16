package com.DI.ApplicationConfigs;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> tClass);

    Reflections getScanner();
}
