package com.senla.di;

import com.senla.di.annotations.Auttowared;
import com.senla.di.annotations.Singleton;

@Singleton
public class configItemsClass {

    @Auttowared
    private configPropertyTest name;


    public configPropertyTest getName() {
        return name;
    }
}
