package com.senla.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuController {

    @Autowired
    private Builder builder;
    @Autowired
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
