package com.senla.model;

import com.senla.di.applicationConfigs.Application;
import com.senla.di.applicationConfigs.ApplicationContext;
import com.senla.model.api.dao.IBookDAO;
import com.senla.model.dao.BookDAO;
import com.senla.model.dao.RequestDAO;
import com.senla.model.exceptions.DAOException;
import com.senla.model.exceptions.ServiceException;
import com.senla.model.models.Book;
import com.senla.model.models.Client;
import com.senla.model.models.Order;
import com.senla.model.models.Request;
import com.senla.model.serializable.Serializer;
import com.senla.model.services.BookService;
import com.senla.model.services.ClientService;

import java.io.*;
import java.lang.reflect.AnnotatedArrayType;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class BookShop {

    // private static BookDAO BOOK_DAO = BookDAO.getInstance();
    //private static OrderDAO ORDER_DAO = OrderDAO.getInstance();
    //private static RequestDAO REQUEST_DAO = RequestDAO.getInstance();
    //private static ClientDAO CLIENT_DAO = ClientDAO.getInstance();
//    private static ClientService clientService = ClientService.getInstance();
    //private static BookService bookService = BookService.getInstance();
    //private static OrderService orderService = OrderService.getInstance();
//    private static RequestService requestService = RequestService.getInstance();
    private static String CONFIG_PATH = "src/com/senla/model/resources/configuration.properties";
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws DAOException, NoSuchFieldException, ServiceException {

       LOGGER.log(Level.INFO, "DASLDASLD");

        ApplicationContext context = Application.run("com.senla", new HashMap<>());

        System.out.println(context.getObject(ClientService.class).getAll());


                                                // SERIALIZATION TO CREATE FILE WITH LIST OF ENTITIES
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /*   List<Book> books = new ArrayList<>();

        books.add(new Book("Alfred Gette", "Detective", 2015, 15));
        books.add(new Book("Outsider", "Horror", 2018, 20));
        books.add(new Book("Jack Richer", "Detective", 2004, 15));

        List<Client> clients = new ArrayList<>();

        clients.add(new Client("Nikko", "nikko.senla@gmail.com"));
        clients.add(new Client("Boby", "boby.senla@gmail.com"));

        List<Request> requests = new ArrayList<Request>();

        requests.add(new Request(books.get(0)));
        requests.add(new Request(books.get(1)));
        requests.add(new Request(books.get(2)));

        List<Order> orders = new ArrayList<>();

        orders.add(new Order(books.get(0), clients.get(0)));
        orders.add(new Order(books.get(1), clients.get(1)));
        orders.add(new Order(books.get(2), clients.get(1)));

        Serializer.serialize(books, clients, requests, orders);

*/


     /*   Optional<String> properties = PropertyHanlder.getProperties("path");
        List<Book> books = new ArrayList<>();
        books.add(new Book());

        //   System.out.println(BOOK_DAO.getAll());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Vasya", "Vasya@gmail.com"));

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(books, clients.get(0)));

        List<Request> requests = new ArrayList<>();
        requests.add(new Request());

        Serializer.serialize(requests, books, orders, clients);

//        System.out.println(BOOK_DAO.getAll());
//        System.out.println(REQUEST_DAO.getAll());
        System.out.println(ORDER_DAO.getAll());

        //   Serializer.serialize(books, clients, orders, requests);

        // Client client1 = new ObjectMapper().readValue(json, Client.class);
        //System.out.println(client1);


        //orderService.getOrderByID(UUID.randomUUID());
        /*

        bookService.addBookToShop("X", "Horror", 2000, 300);
        bookService.addBookToShop("Z", "Horror", 2000, 200);
        bookService.addBookToShop("AAA", "Horror", 2015, 1000);

        System.out.println(bookService.getSortedBooks("ByDateOfAdmission") );

        Book book = new Book("Genesis", "Traveling", 2018, 200);
        Book book1 = new Book("Apocalipse", "Action", 1999, 500);


        bookService.add(book1);

        System.out.println(bookService.getAll());

        bookService.updateBook(book1.getUuid(), book);

        System.out.println(bookService.getAll());


     //   System.out.println(bookService.getAll());
        Client client = new Client("Robby");

        Order order2 = new Order(client);

        order2.setDateOfExecution( LocalDate.of(2018,12,5));

        order2.setStatus(OrderStatus.CLOSED);

      //  System.out.println(orderService.getClosedOrdersByTime( LocalDate.of(2018,12,5 ),  LocalDate.of(2030,6,15), new OrderByDateOfExecutionComparator()));       // System.out.println(orderService.getSortedOrders(new OrderByStatusComparator()));

        List<Request> list = new ArrayList<Request>();
*/
        //  System.out.println(bookService.getAll());
        //  System.out.println(bookService.getSortedBooks(new BookDateOfWritingComparator()));


    }

}
