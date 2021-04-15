package com.senal.ui;

import com.senla.di.annotations.Auttowared;

import java.util.Scanner;

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
