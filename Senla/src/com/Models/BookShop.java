package com.Models;

import com.DI.ApplicationConfigs.Application;
import com.DI.ApplicationConfigs.ApplicationContext;
import com.Models.DAO.BookDAO;
import com.Models.DAO.ClientDAO;
import com.Models.DAO.OrderDAO;
import com.Models.DAO.RequestDAO;
import com.Models.Models.Book;
import com.Models.Services.BookService;
import com.Models.Services.ClientService;
import com.Models.Services.OrderService;
import com.Models.exceptions.DAOException;


import java.util.*;


public class BookShop {

    private static BookDAO BOOK_DAO = BookDAO.getInstance();
    private static OrderDAO ORDER_DAO = OrderDAO.getInstance();
    private static RequestDAO REQUEST_DAO = RequestDAO.getInstance();
    private static ClientDAO CLIENT_DAO = ClientDAO.getInstance();
//    private static ClientService clientService = ClientService.getInstance();
    private static BookService bookService = BookService.getInstance();
    private static OrderService orderService = OrderService.getInstance();
//    private static RequestService requestService = RequestService.getInstance();


    public static void main(String[] args) throws DAOException {


        ApplicationContext context = Application.run("com.DI",
                new HashMap<>());

        System.out.println(context.getObject(ClientService.class));
        System.out.println(context.getObject(Book.class));
        System.out.println(context.getObject(Book.class));
        System.out.println(context.getObject(ClientService.class));




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
