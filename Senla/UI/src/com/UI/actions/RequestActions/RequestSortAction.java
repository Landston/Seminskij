package com.UI.actions.RequestActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

public class RequestSortAction extends BaseAction implements IAction {
    @Override
    public void execute() {
        try{
            System.out.println(facade.getAllRequests());
        }catch (Exception e){

        }
    }
}
