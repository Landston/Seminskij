package com.UI.actions;



import com.Models.BookShopFacade;

import java.util.logging.Logger;

public abstract class BaseAction {

    public BookShopFacade facade = BookShopFacade.getInstance();
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

}
