package com.senla.ui;

import com.senla.ui.actions.request.RequestSortAction;

import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;


import com.senla.ui.actions.client.CreateClientAction;
import com.senla.ui.actions.client.DeleteClientAction;
import com.senla.ui.actions.client.UpdateClientAction;
import com.senla.ui.actions.request.CreateRequestAction;
import com.senla.ui.actions.exitAction;
import com.senla.model.facade.BookShopFacade;
import com.senla.ui.actions.book.*;
import com.senla.ui.actions.order.*;

@Singleton
public class Builder {


    private Menu rootMenu;

    @Auttowared
    private BookShopFacade facade;

    public Builder() {

    }


    public void buildMenu() {  // Создаём начальное меню
        this.rootMenu = new Menu("Начальное меню");

        this.rootMenu.addMenuItem(new MenuItem("Выход из программы", new exitAction(), new Menu("Thanks for coming")));
        this.rootMenu.addMenuItem(new MenuItem("Вернуться в начальное меню", () -> {
        }, rootMenu));
        this.rootMenu.addMenuItem(new MenuItem("Работа с книгами", () -> {
        }, buildBookMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Работа с заказами", () -> {
        }, buildOrderMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Работа с запросами", () -> {
        }, buildRequestMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Работа с клиентами", () -> {
        }, buildClientMenu()));
        this.rootMenu.addMenuItem(new MenuItem("Сумма заработанных средств за период", new TotalCostCountingAction(), rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu buildClientMenu() {
        Menu clientMenu = new Menu("Клиентское меню");

        clientMenu.addMenuItem(new MenuItem("Выход из программы", new exitAction(), new Menu("End")));
        clientMenu.addMenuItem(new MenuItem("Вернуться в начальное меню", () -> {
        }, rootMenu));
        clientMenu.addMenuItem(new MenuItem("Добавить клиента в систему", new CreateClientAction(), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Изменить клиента ", new UpdateClientAction(), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Удалить клиента ", new DeleteClientAction(), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Список клиентов ", () -> {

            System.out.println(facade.getAllClients());
        }, rootMenu));

        return clientMenu;
    }

    private Menu buildBookMenu() {
        Menu bookMenu = new Menu("Книжное меню");

        bookMenu.addMenuItem(new MenuItem("Выход из программы", new exitAction(), new Menu("End")));
        bookMenu.addMenuItem(new MenuItem("Вернуться в начальное меню", () -> {
        }, rootMenu));
        bookMenu.addMenuItem(new MenuItem("Создать новую книгу", new BookCreateAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Удалить книгу", new BookDeleteAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Изменить книгу", new BookUpdateAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Поставить книгу на склад", new BookAddToWhareHouseAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Списать книгу со складов", new BookWritingOffAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Список", new BookSortingAction(), rootMenu));

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
        Menu orderMenu = new Menu("Меню работы с Заказами");

        orderMenu.addMenuItem(new MenuItem("Выход из программы", new exitAction(), new Menu("End")));
        orderMenu.addMenuItem(new MenuItem("Вернуться в начальное меню", () -> {
        }, rootMenu));
        orderMenu.addMenuItem(new MenuItem("Создать новый заказ", new CreateOrderAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Посмотреть детали заказа", new OrderDetailsAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Удалить заказ", new DeleteOrderAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Изменить состояние заказа", new OrderChangeStatusAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Изменить заказ", new OrderUpdateAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Список", new SortOrderAction(), rootMenu));

        return orderMenu;
    }


    private Menu buildRequestMenu() {
        Menu requestMenu = new Menu("Меняю работы с запросами");

        requestMenu.addMenuItem(new MenuItem("Выход из программы", new exitAction(), new Menu("End")));
        requestMenu.addMenuItem(new MenuItem("Вернуться в начальное меню", () -> {
        }, rootMenu));
        requestMenu.addMenuItem(new MenuItem("Создать запрос на книгу", new CreateRequestAction(), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Список запросов", new RequestSortAction(), rootMenu));

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
