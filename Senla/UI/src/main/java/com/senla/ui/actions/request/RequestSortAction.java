package com.senla.ui.actions.request;

import com.senla.model.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

public class RequestSortAction extends BaseAction implements IAction {
    public RequestSortAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try{
            System.out.println(facade.getAllRequests());
        }catch (Exception e){

        }
    }
}
