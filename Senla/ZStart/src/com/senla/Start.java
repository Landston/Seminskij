package com.senla;

import com.senla.di.appconfig.Application;
import com.senla.di.appconfig.ApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Start {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/com/senla/model/resource/logger.properties"));
        } catch (IOException e) {
            System.out.println("Logger properties not found");
        }


        ApplicationContext context = ApplicationContext.getInstance();



        System.out.println();
    }

    }

