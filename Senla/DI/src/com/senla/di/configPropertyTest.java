package com.senla.di;

import com.senla.di.annotations.ConfigProperty;
import com.senla.di.annotations.Singleton;

@Singleton
public class configPropertyTest {


    public String getMonth() {
        return month;
    }

    @ConfigProperty(configName = "src/com/senla/model/resources/configProperty.properties", propertyName = "month")
    private String month;


}
