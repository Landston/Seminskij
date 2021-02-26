package com.UI.actions.RequestActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CreateRequestAction extends BaseAction implements IAction {


    @Override
    public void execute() {
        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllBooks())){

            System.out.println(count + " " + item);

            count++;
        }
        System.out.println("Enter id of a Book that you want to update");

        Scanner scanner = new Scanner(System.in);
        String uuid = scanner.nextLine();

        this.facade.createRequest(UUID.fromString(uuid));

    }
}
