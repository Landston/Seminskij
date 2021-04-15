package com.senla.di.appconfig.api;

import com.senla.di.appconfig.ApplicationContext;

public interface ObjectConfigurator {

    void configure(Object t, ApplicationContext context);
}
