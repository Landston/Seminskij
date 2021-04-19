package com.senla.ui.configuration;

import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Configuration;
import com.senla.ui.Builder;
import com.senla.ui.MenuController;
import com.senla.ui.Navigator;

@Configuration
public class UserInterfaceConfiguration {

    @Auttowared
    private Builder builder;
    @Auttowared
    private Navigator navigator;
    @Auttowared
    private MenuController menuController;

}
