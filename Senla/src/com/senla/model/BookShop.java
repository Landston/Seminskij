package com.senla.model;

import com.senla.di.appconfig.Application;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.model.api.dao.IBookDAO;
import com.senla.model.api.dao.IClientDAO;
import com.senla.model.api.dao.IOrderDAO;
import com.senla.model.api.dao.IRequestDAO;
import com.senla.model.configuration.DataBaseHandler;
import com.senla.model.dao.*;
import com.senla.model.exception.DAOException;
import com.senla.model.exception.ServiceException;
import com.senla.model.model.*;
import com.senla.model.serializable.Serializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class BookShop {

    private static String CONFIG_PATH = "src/com/senla/model/resource/configuration.properties";
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private static String URL = "jdbc:postgresql://localhost:5432/bookshop";
    private static String USER = "postgres";
    private static String PASS = "root";
    private ApplicationContext context;

    public static void main(String[] args) throws DAOException, NoSuchFieldException, ServiceException, SQLException {


        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/com/senla/model/resource/logger.properties"));
        } catch (IOException e) {
            System.out.println("Logger properties not found");
        }
        LOGGER.log(Level.INFO, "BookShop main class, logger configured successfully");

        System.out.println(UUID.randomUUID());

        ApplicationContext.getInstance().getObject(IRequestDAO.class);

    }


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
    }

}
