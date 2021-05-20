package com.senla.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
