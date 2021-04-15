package com.senal.ui.actions.request;

import com.senal.ui.actions.BaseAction;
import com.senal.ui.actions.IAction;

public class RequestSortAction extends BaseAction implements IAction {
    @Override
    public void execute() {
        try{
            System.out.println(facade.getAllRequests());
        }catch (Exception e){

        }
    }
}
