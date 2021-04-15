package com.senla.di.handler;

import com.senla.di.appconfig.ApplicationContext;
import com.senla.di.appconfig.api.ObjectConfigurator;
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
