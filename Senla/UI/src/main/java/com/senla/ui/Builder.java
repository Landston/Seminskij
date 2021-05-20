package com.senla.ui;

import com.senla.ui.actions.request.RequestSortAction;



import com.senla.ui.actions.client.CreateClientAction;
import com.senla.ui.actions.client.DeleteClientAction;
import com.senla.ui.actions.client.UpdateClientAction;
import com.senla.ui.actions.request.CreateRequestAction;
import com.senla.ui.actions.exitAction;
import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.book.*;
import com.senla.ui.actions.order.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Builder {

    @Autowired
    private BookShopFacade facade;
    private Menu rootMenu;
    private static final Logger LOGGER = LogManager.getLogger(Builder.class);

    public Builder() {

    }


    public void buildMenu() {  // Создаём начальное меню
        this.rootMenu = new Menu("Start menu");

        this.rootMenu.addMenuItem(new MenuItem("Exit program", new exitAction(facade), new Menu("Thanks for coming")));
        this.rootMenu.addMenuItem(new MenuItem("Back to root menu", () -> {
        }, rootMenu));
        this.rootMenu.addMenuItem(new MenuItem("Books", () -> {
        }, buildBookMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Orders", () -> {
        }, buildOrderMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Requests", () -> {
        }, buildRequestMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Clients", () -> {
        }, buildClientMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Income from books", new TotalCostCountingAction(facade), rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu buildClientMenu() {
        Menu clientMenu = new Menu("Client menu");

        clientMenu.addMenuItem(new MenuItem("Exit program", new exitAction(facade), new Menu("End")));
        clientMenu.addMenuItem(new MenuItem("Back to root menu", () -> {
        }, rootMenu));
        clientMenu.addMenuItem(new MenuItem("Add client ", new CreateClientAction(facade), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Update Client ", new UpdateClientAction(facade), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Delete Client ", new DeleteClientAction(facade), rootMenu));
        clientMenu.addMenuItem(new MenuItem("List of Clients ", () -> {

            try {
                System.out.println(facade.getAllClients());
            } catch (Exception e) {
                e.printStackTrace();

            }
        }, rootMenu));

        return clientMenu;
    }

    private Menu buildBookMenu() {
        Menu bookMenu = new Menu("Books menu");

        bookMenu.addMenuItem(new MenuItem("Exit program", new exitAction(facade), new Menu("End")));
        bookMenu.addMenuItem(new MenuItem("Back to start menu", () -> {
        }, rootMenu));
        bookMenu.addMenuItem(new MenuItem("Add book to shop", new BookCreateAction(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Delete book", new BookDeleteAction(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Update book", new BookUpdateAction(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Add book to warehouse", new BookAddToWhareHouseAction(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Write off book from warehouse", new BookWritingOffAction(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("List of books", new BookSortingAction(facade), rootMenu));

        return bookMenu;
    }
/*
    private Menu buildSortingBookMenu(){
        Menu bookSortingMenu = new Menu("Получение книг");

        bookSortingMenu.addMenuItem(new MenuItem("Выход", new exitAction(), rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Весь список книг", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getAllBooks());
        }, rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Список книг отсортированный по Алфавиту", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedBooks("Alphabet"));
        }, rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Список книг отсортированный по Цене", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedBooks("ByCost"));
        }, rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Список книг отсортированный по дате поуступления", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedBooks("ByDateOfAdmission"));
        }, rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Список книг отсортированный по дате написания", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedBooks("ByDateOfWriting"));
        }, rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Список книг отсортированный по доступности",  ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedBooks("Reserved"));
        }, rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Список книг залежавшихся книг по дате поступления  ", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getBookThatAreNotSoldBySixMonths("ByDateOfAdmission"));
        }, rootMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Список книг залежавшихся книг по цене", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getBookThatAreNotSoldBySixMonths("ByCost"));
        }, rootMenu));

        return bookSortingMenu;
    }*/

    private Menu buildOrderMenu() {
        Menu orderMenu = new Menu("Order menu");

        orderMenu.addMenuItem(new MenuItem("Exit program", new exitAction(facade), new Menu("End")));
        orderMenu.addMenuItem(new MenuItem("Back to Start menu", () -> {
        }, rootMenu));
        orderMenu.addMenuItem(new MenuItem("Add Order", new CreateOrderAction(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Order Details", new OrderDetailsAction(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Delete Order", new DeleteOrderAction(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Change Order status", new OrderChangeStatusAction(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Update Order", new OrderUpdateAction(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("List of Order", new SortOrderAction(facade), rootMenu));

        return orderMenu;
    }


    private Menu buildRequestMenu() {
        Menu requestMenu = new Menu("Request menu");

        requestMenu.addMenuItem(new MenuItem("Exit program", new exitAction(facade), new Menu("End")));
        requestMenu.addMenuItem(new MenuItem("Back to root menu", () -> {
        }, rootMenu));
        requestMenu.addMenuItem(new MenuItem("Add Request", new CreateRequestAction(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("List of Requests", new RequestSortAction(facade), rootMenu));

        return requestMenu;
    }

   /* private Menu buildSortingRequestMenu() {
        Menu requestSortMenu = new Menu("Список запросов");

        requestSortMenu.addMenuItem(new MenuItem("Выход", new exitAction(), rootMenu));
        requestSortMenu.addMenuItem(new MenuItem("Список всех запросов", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getAllRequests());
        }, rootMenu));
        requestSortMenu.addMenuItem(new MenuItem("Список всех запросов по алфавиту",  ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedOrder("ByCount"));
        }, rootMenu));
        requestSortMenu.addMenuItem(new MenuItem("Список всех запросов по количеству", (int o1, int o2)-> System.out.println("asdasd"), rootMenu));

        return requestSortMenu;
    }*/
}
