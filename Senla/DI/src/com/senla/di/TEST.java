package com.senla.di;

import com.senla.di.applicationConfigs.Application;
import com.senla.di.applicationConfigs.ApplicationContext;

import java.util.HashMap;

public class TEST {
    public static void main(String[] args) {

        ApplicationContext context = Application.run("com.senla", new HashMap<>());

        //   context.getObject(configPropertyTest.class);

        context.getObject(configPropertyTest.class);

        System.out.println( context.getObject(configItemsClass.class).getName().getMonth());

        System.out.println();

    }
}
