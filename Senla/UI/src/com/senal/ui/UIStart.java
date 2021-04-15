package com.senal.ui;

import com.senla.di.applicationConfigs.Application;
import com.senla.di.applicationConfigs.ApplicationContext;
import com.senla.di.configItemsClass;
import com.senla.di.configPropertyTest;
import com.senla.model.BookShopFacade;
import com.senla.model.api.dao.IBookDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UIStart {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {


        //   context.getObject(configPropertyTest.class);

    //   System.out.println(context.getObject(configItemsClass.class).getName().getMonth());

        ApplicationContext context = Application.run("com.senla", new HashMap<>());

        context.getObject(BookShopFacade.class);
        context.getObject(Builder.class);

        System.out.println();
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/com/senla/model/resources/logger.properties"));
        } catch (IOException e) {
            System.out.println("Logger properties not found");
        }


       LOGGER.log(Level.INFO, "WORK PLEASE");


        context.getObject(MenuController.class).run();

    }




}
