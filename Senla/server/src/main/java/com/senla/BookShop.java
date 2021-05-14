package com.senla;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.sql.*;

public class BookShop {

    private static String CONFIG_PATH = "java/com/senla/model/resource/configuration.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookShop.class.getName());
    private static String URL = "jdbc:postgresql://localhost:5432/bookshop";
    private static String USER = "postgres";
    private static String PASS = "root";

    public static void main(String[] args) throws DAOException, NoSuchFieldException, ServiceException, SQLException, ClassNotFoundException {

    }
}




/*
    private static void serialize() throws DAOException {
        List<Book> books = new ArrayList<>();

        books.add(new Book("Alfred Gette", "Detective", 2015, 15));
        books.get(0).setStatus(BookStatus.ABSENT);
        books.add(new Book("Outsider", "Horror", 2018, 20));
        books.add(new Book("Jack Richer", "Detective", 2004, 15));

        List<Client> clients = new ArrayList<>();

        clients.add(new Client("Nikko", "nikko.senla@gmail.com"));
        clients.add(new Client("Boby", "boby.senla@gmail.com"));

        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(books.get(0)));
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(books.get(0), clients.get(0)));
        orders.add(new Order(books.get(1), clients.get(1)));
        orders.add(new Order(books.get(2), clients.get(1)));

        Serializer.serialize(books, clients, requests, orders);

    }

    private static void desirialize() throws DAOException {
        ApplicationContext context = ApplicationContext.getInstance();
        List<Book> books = new ArrayList<>(context.getObject(BookDAO.class).getAll());
        List<Client> clients = new ArrayList<>(context.getObject(ClientDAO.class).getAll());
        List<Request> requests = new ArrayList<Request>(context.getObject(RequestDAO.class).getAll());
        List<Order> orders = new ArrayList<>(context.getObject(OrderDAO.class).getAll());

        Serializer.serialize(books, clients, requests, orders);
    }*/


