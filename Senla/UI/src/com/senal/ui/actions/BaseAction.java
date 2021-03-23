package com.senal.ui.actions;



import com.senla.di.annotations.Auttowared;
import com.senla.di.annotations.Configuration;
import com.senla.model.BookShopFacade;

import java.util.logging.Logger;


public abstract class BaseAction {

    @Auttowared
    public BookShopFacade facade;

    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

}
