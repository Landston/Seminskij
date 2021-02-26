package com.UI;

import com.Models.BookShopFacade;
import com.UI.actions.BookActions.*;
import com.UI.actions.ClientActions.CreateClientAction;
import com.UI.actions.ClientActions.DeleteClientAction;
import com.UI.actions.ClientActions.UpdateClientAction;
import com.UI.actions.OrderActions.*;
import com.UI.actions.RequestActions.CreateRequestAction;
import com.UI.actions.exitAction;

public class Builder {

    private static Builder insance = new Builder();
    private Menu rootMenu;

    private Builder(){

    }

    public static Builder getInsance(){
        if(insance == null) insance = new Builder();
        return insance;
    }

    public void buildMenu(){  // Создаём начальное меню
      this.rootMenu = new Menu("Начальное меню");

      this.rootMenu.addMenuItem(new MenuItem("Выход", new exitAction(), new Menu("Thanks for coming")));
      this.rootMenu.addMenuItem(new MenuItem("Работа с книгами", ()-> {}  , buildBookMenu()));
      this.rootMenu.addMenuItem(new MenuItem("Работа с заказами", ()-> {}, buildOrderMenu()));
      this.rootMenu.addMenuItem(new MenuItem("Работа с запросами", ()-> {}, buildRequestMenu()));
      this.rootMenu.addMenuItem(new MenuItem("Работа с клиентами", ()-> {}, buildClientMenu()));
      this.rootMenu.addMenuItem(new MenuItem("Сумма заработанных средств за период", new TotalCostCountingAction(), rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu buildClientMenu(){
        Menu clientMenu = new Menu("Клиентское меню");

        clientMenu.addMenuItem(new MenuItem("Выход", new exitAction(), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Добавить клиента в систему", new CreateClientAction(), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Изменить клиента ", new UpdateClientAction(), rootMenu));
        clientMenu.addMenuItem(new MenuItem("Удалить клиента ", new DeleteClientAction(), rootMenu));

        return  clientMenu;
    }

    private Menu buildBookMenu(){
        Menu bookMenu = new Menu("Книжное меню");

        bookMenu.addMenuItem(new MenuItem("Выход", new exitAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Создать новую книгу", new BookCreateAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Удалить книгу", new BookDeleteAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Изменить книгу", new BookUpdateAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Поставить книгу на склад", new BookAddToWhareHouseAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Списать книгу со складов", new BookWritingOffAction(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Список", ()->{   }, buildSortingBookMenu()));

        return bookMenu;
    }

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
    }

    private Menu buildOrderMenu(){
        Menu orderMenu = new Menu("Меню работы с Заказами");

        orderMenu.addMenuItem(new MenuItem("Выход", new exitAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Создать новый заказ", new CreateOrderAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Посмотреть детали заказа", new OrderDetailsAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Удалить заказ", new DeleteOrderAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Изменить состояние заказа", new OrderChangeStatusAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Изменить заказ", new OrderUpdateAction(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Список", ()->{}, buildSortingOrderMenu()));

        return orderMenu;
    }

    private Menu buildSortingOrderMenu(){
        Menu menu = new Menu("Список заказов");

        menu.addMenuItem(new MenuItem("Выход", new exitAction(), rootMenu));
        menu.addMenuItem(new MenuItem("Весь список заказов", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getAllOrders());
        }, rootMenu));
        menu.addMenuItem(new MenuItem("Cписок заказов по цене", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedOrder("ByCost"));
        }, rootMenu));
        menu.addMenuItem(new MenuItem("Cписок заказов по дате завершения", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedOrder("ByDateOfExecution"));
        }, rootMenu));
        menu.addMenuItem(new MenuItem("Cписок заказов по статусу", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedOrder("ByStatus"));
        }, rootMenu));
        menu.addMenuItem(new MenuItem("Cписок выполненых за период заказов", new ListClosedOrdersByTimeAction(), rootMenu));

        return menu ;
    }

    private Menu buildRequestMenu(){
        Menu requestMenu = new Menu("Меняю работы с запросами");

        requestMenu.addMenuItem(new MenuItem("Выход", new exitAction(), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Создать запрос на книгу",  new CreateRequestAction(), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Список запросов", ()->{}, buildSortingRequestMenu()));

        return requestMenu;
    }

    private Menu buildSortingRequestMenu() {
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
        requestSortMenu.addMenuItem(new MenuItem("Список всех запросов по количеству", ()->{
            BookShopFacade facade = BookShopFacade.getInstance();
            System.out.println(facade.getSortedOrder("ByName"));
        }, rootMenu));

        return requestSortMenu;
    }
}
