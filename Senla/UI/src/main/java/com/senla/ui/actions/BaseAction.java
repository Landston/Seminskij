package com.senla.ui.actions;


import com.senla.facade.BookShopFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class BaseAction {

    protected BookShopFacade facade;

    protected final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    public BaseAction(BookShopFacade facade){
        this.facade = facade;
    }

}
