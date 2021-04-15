package com.senla.ui.actions;


import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;
import com.senla.model.facade.BookShopFacade;


import java.util.logging.Logger;


public class BaseAction {
    @Auttowared
    public BookShopFacade facade;

    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());


}
