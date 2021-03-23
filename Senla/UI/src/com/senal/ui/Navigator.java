package com.senal.ui;

import com.senla.di.annotations.Singleton;

@Singleton
public class Navigator {

    private Menu currentMenu;

    public Navigator(){

    }

    public void printMenu(){
        int count = 0;

        for (MenuItem item : this.currentMenu.getMenuItems()){
            System.out.println(count + " " + item.getName());

            count++;
        }
    }

    public void navigate(Integer index){
        try {
            MenuItem menuItem = currentMenu.getMenuItems().get(index);
            if(menuItem != null) {
                menuItem.doAction();
                currentMenu = menuItem.getNextMenu();
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Неверный индекс");
        }


    }

    public  void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
