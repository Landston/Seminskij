package com.senla.ui;

import com.senla.ui.actions.IAction;

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
        System.out.println(name + "\n");
        action.execute();


    }

    public String getName(){
        return this.name;
    }
    public Menu getNextMenu() {
        return nextMenu;
    }

    public IAction getAction(){
        return this.action;
    }
}
