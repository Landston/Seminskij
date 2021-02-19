package com.Models;

import com.Models.DAO.BookDAO;
import com.Models.DAO.ClientDAO;
import com.Models.DAO.OrderDAO;
import com.Models.DAO.RequestDAO;
import com.Models.Models.Book;
import com.Models.Services.BookService;
import com.Models.Services.ClientService;
import com.Models.Services.OrderService;
import com.Models.Services.RequestService;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookShopFacade {

    private static final BookShopFacade instance = new BookShopFacade();
    private final BookService bookService;
    private final ClientService clientService;
    private final OrderService orderService;
    private final RequestService requestService;

    private BookShopFacade() {
        bookService = new BookService(new BookDAO());
        clientService = new ClientService(new ClientDAO());
        orderService = new OrderService(new OrderDAO());
        requestService = new RequestService(new RequestDAO());

    }

    public List<Book> getAllBooks() {
        return bookService.getAll();

    }

    public void addBookToShop(String name, String gonre, int year, double cost){
        this.bookService.addBookToShop(name,gonre,year,cost);

        System.out.println("Book has added ");

    }


    public static BookShopFacade getInstance() {
        return Objects.requireNonNullElse(instance, new BookShopFacade());
    }


}
