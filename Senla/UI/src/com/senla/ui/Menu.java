package com.senla.ui;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<MenuItem> menuItems = new ArrayList<>();
    private String name;

    public Menu(String name) {
        this.name = name;
    }


    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

   public void addMenuItem(MenuItem item){
        menuItems.add(item);
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
