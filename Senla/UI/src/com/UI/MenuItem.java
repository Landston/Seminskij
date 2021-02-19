package com.UI;

import com.UI.actions.IAction;

public class MenuItem {
    @Override
    public String toString() {
        return name + '\n';
    }

    private String name;
    private IAction action;
    private Menu nextMenu;


    public MenuItem(String name, IAction action, Menu nextMenu) {
        this.name = name;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() {
        System.out.println(name);
        action.execute();

    }

    public Menu getNextMenu() {
        return nextMenu;
    }

}
