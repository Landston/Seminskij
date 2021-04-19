package com.senla.ui;

import com.senla.di.appconfig.Application;
import com.senla.di.appconfig.ApplicationContext;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UIStart {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        ApplicationContext context = Application.run("com.senla", new HashMap<>());


        System.out.println();


        context.getObject(MenuController.class).run();
    }


}
