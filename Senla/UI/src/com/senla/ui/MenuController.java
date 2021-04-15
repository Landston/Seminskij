package com.senla.ui;

import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;

import java.util.Scanner;

@Singleton
public class MenuController {

    @Auttowared
    private Builder builder;
    @Auttowared
    private Navigator navigator;

    public MenuController() {

    }

    public void run() {
        Scanner line = new Scanner(System.in);
        builder.buildMenu();
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



}
