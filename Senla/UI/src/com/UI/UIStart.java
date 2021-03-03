package com.UI;

import com.Models.BookShopFacade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UIStart {

    public static void main(String[] args) {

        BookShopFacade facade = BookShopFacade.getInstance();
        

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("D:\\GITLABA\\Senla\\src\\com\\Models\\resources\\logger.properties"));
        } catch (IOException e) {
            System.out.println("Logger properties not found");
        }

        MenuController menuController = MenuController.getInstance();

        menuController.run();
    }


}
