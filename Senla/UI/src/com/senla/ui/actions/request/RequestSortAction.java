package com.senla.ui.actions.request;

import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

public class RequestSortAction extends BaseAction implements IAction {
    @Override
    public void execute() {
        try{
            System.out.println(facade.getAllRequests());
        }catch (Exception e){

        }
    }
}
