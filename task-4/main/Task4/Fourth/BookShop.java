package main.Task3.Fourth;

import com.sun.jdi.request.DuplicateRequestException;
import main.Task3.Fourth.Comparators.BookComparators.BookDateOfAdmission;
import main.Task3.Fourth.Comparators.OrderCorparators.OrderByDateOfExecutionComparator;
import main.Task3.Fourth.Comparators.OrderCorparators.OrderByStatusComparator;
import main.Task3.Fourth.DAO.BookDAO;
import main.Task3.Fourth.DAO.OrderDAO;
import main.Task3.Fourth.DAO.RequestDAO;
import main.Task3.Fourth.Models.*;
import main.Task3.Fourth.Services.BookService;
import main.Task3.Fourth.Services.OrderService;
import main.Task3.Fourth.Services.RequestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BookShop {

    private static BookDAO BOOK_DAO = new BookDAO(new ArrayList<Book>());
    private static OrderDAO ORDER_DAO = new OrderDAO(new ArrayList<Order>());
    private static RequestDAO REQUEST_DAO = new RequestDAO(new ArrayList<Request>());
    private static final BookService bookService = new BookService(BOOK_DAO);
    private static final OrderService orderService = new OrderService(ORDER_DAO);
    private static final RequestService requestDAO = new RequestService(REQUEST_DAO);



    public static void main(String[] args) {


        Book book = new Book("YU", "Detective", 2015, 150);

        Book book1 = new Book("X", "Horror", 2000, 200);

        Book book2 = new Book("Z", "Horror", 2000, 300);

        Order order1 = new Order(new Client("Vasyan"));
        ORDER_DAO.addEntity(order1);

        Order order2 = new Order(new Client("Petya"));

        ORDER_DAO.addEntity(order2);

        BOOK_DAO.addEntity(book);
        BOOK_DAO.addEntity(book1);
        BOOK_DAO.addEntity(book2);

        order2.setDateOfExecution( LocalDate.of(2018,12,5));

        order2.setStatus(OrderStatus.CLOSED);

      //  System.out.println(orderService.getClosedOrdersByTime( LocalDate.of(2018,12,5 ),  LocalDate.of(2030,6,15), new OrderByDateOfExecutionComparator()));       // System.out.println(orderService.getSortedOrders(new OrderByStatusComparator()));

        List<Request> list = new ArrayList<Request>();

        bookService.delete(book1);


      //  System.out.println(bookService.getAll());
      //  System.out.println(bookService.getSortedBooks(new BookDateOfWritingComparator()));


    }

}
