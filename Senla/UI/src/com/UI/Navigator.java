package com.UI;

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

        for(MenuItem item : currentMenu.getMenuItems()){

            System.out.println(item);
        }
    }

    public void navigate(Integer index){
        MenuItem menu = currentMenu.getMenuItems().get(index);
        if(menu != null) {
            menu.doAction();
            currentMenu = menu.getNextMenu();
        }


    }

    public  void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
