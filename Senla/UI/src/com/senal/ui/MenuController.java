package com.senal.ui;

import java.util.Scanner;

public class MenuController {

    private static MenuController instance = new MenuController();
    private Builder builder;
    private Navigator navigator;

    private MenuController() {

    }

    public void run() {
        Scanner line = new Scanner(System.in);
        builder = Builder.getInsance();
        builder.buildMenu();
        navigator = Navigator.getInstance();
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index = -2;

        while(!index.equals(0)){
            System.out.println("Выберите пункт меню: ");
            index = line.nextInt();
            navigator.navigate(index);
            navigator.printMenu();


        }
    }

    public static MenuController getInstance() {
        if (instance == null) instance = new MenuController();
        return instance;
    }


}
