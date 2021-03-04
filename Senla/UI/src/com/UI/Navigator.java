package com.UI;

import com.UI.actions.exitAction;

public class Navigator {

    private static Navigator instance = new Navigator();
    private Menu currentMenu;

    private Navigator(){

    }

    public static Navigator getInstance(){
        if(instance == null) new Navigator();
        return instance;
    }
    public void printMenu(){
        int count = 0;

        for (MenuItem item : this.currentMenu.getMenuItems()){
            System.out.println(count + " " + item.getName());

            count++;
        }
    }

    public void navigate(Integer index){
        MenuItem menuItem = currentMenu.getMenuItems().get(index);

        if(menuItem != null) {
                menuItem.doAction();
            currentMenu = menuItem.getNextMenu();
        }

    }

    public  void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
