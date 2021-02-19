package com.UI;

import com.UI.actions.BookCreateAction;

public class Builder {

    private static Builder insance = new Builder();
    private Menu rootMenu;

    private Builder(){

    }

    public static Builder getInsance(){
        if(insance == null) insance = new Builder();
        return insance;
    }

    public void buildMenu(){  // Создаём начальное меню
      this.buildBookMenu();
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    private void buildBookMenu(){
        Menu menu = new Menu();
        MenuItem item = new MenuItem("Создать новую книгу", new BookCreateAction(), rootMenu);
        menu.addMenuItem(item);

        rootMenu = menu;
    }


}
