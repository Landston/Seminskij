package com.senla;

import com.senla.di.applicationConfigs.Application;
import com.senla.di.applicationConfigs.ApplicationContext;

import java.util.HashMap;

public class Start {

    public static void main(String[] args) {

        ApplicationContext context = Application.run("com.senla", new HashMap<>());


    }
}
