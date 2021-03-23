package com.senla.di.annotationhandlers;

import com.senla.di.applicationConfigs.ApplicationContext;
import com.senla.di.applicationConfigs.ObjectConfigurator;
import lombok.SneakyThrows;

public class SingletonAnnotationHandler implements ObjectConfigurator {


    @SneakyThrows
    public SingletonAnnotationHandler() {

    }

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {

    }


}
