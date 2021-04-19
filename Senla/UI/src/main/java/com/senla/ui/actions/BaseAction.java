package com.senla.ui.actions;


import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;
import com.senla.model.facade.BookShopFacade;


import java.util.logging.Logger;


public class BaseAction {

    protected BookShopFacade facade;

    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public BaseAction(BookShopFacade facade){
        this.facade = facade;
    }

}
