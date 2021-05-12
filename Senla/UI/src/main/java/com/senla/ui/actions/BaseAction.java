package com.senla.ui.actions;


import com.senla.facade.BookShopFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseAction {

    protected BookShopFacade facade;

    protected final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    public BaseAction(BookShopFacade facade){
        this.facade = facade;
    }

}
