package com.UI;

import java.util.Scanner;

public class MenuController {

    private static MenuController instance = new MenuController();
    private Builder builder;
    private Navigator navigator;

    private MenuController() {

    }

    public void run() {
        builder = Builder.getInsance();
        builder.buildMenu();
        navigator = Navigator.getInstance();
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index = -1;

        while(!index.equals(0)){
            System.out.println("Выберите пункт меню: ");
            Scanner line = new Scanner(System.in);
            index = line.nextInt();
            navigator.navigate(index);
            navigator.setCurrentMenu(builder.getRootMenu());
            navigator.printMenu();


        }
    }

    public static MenuController getInstance() {
        if (instance == null) instance = new MenuController();
        return instance;
    }


}
