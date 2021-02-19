package com.Models;

import com.Models.DAO.BookDAO;
import com.Models.DAO.ClientDAO;
import com.Models.DAO.OrderDAO;
import com.Models.DAO.RequestDAO;
import com.Models.Services.BookService;
import com.Models.Services.ClientService;
import com.Models.Services.OrderService;
import com.Models.Services.RequestService;
import com.Models.Models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BookShop {

   private static BookDAO BOOK_DAO = new BookDAO();
   private static OrderDAO ORDER_DAO = new OrderDAO();
   private static RequestDAO REQUEST_DAO = new RequestDAO();
   private static ClientDAO CLIENT_DAO= new ClientDAO();
   private static ClientService clientService = new ClientService(CLIENT_DAO);
   private static BookService bookService = new BookService(BOOK_DAO);
   private static OrderService orderService = new OrderService(ORDER_DAO);
   private static RequestService requestService = new RequestService(REQUEST_DAO);



    public static void main(String[] args)  {


        bookService.addBookToShop("X", "Horror", 2000, 300);
        bookService.addBookToShop("Z", "Horror", 2000, 200);
        bookService.addBookToShop("AAA", "Horror", 2015, 1000);

        System.out.println(bookService.getSortedBooks("ByDateOfAdmission") );

     //   System.out.println(bookService.getAll());
        Client client = new Client("Robby");

        Order order2 = new Order(client);

        order2.setDateOfExecution( LocalDate.of(2018,12,5));

        order2.setStatus(OrderStatus.CLOSED);

      //  System.out.println(orderService.getClosedOrdersByTime( LocalDate.of(2018,12,5 ),  LocalDate.of(2030,6,15), new OrderByDateOfExecutionComparator()));       // System.out.println(orderService.getSortedOrders(new OrderByStatusComparator()));

        List<Request> list = new ArrayList<Request>();

      //  System.out.println(bookService.getAll());
      //  System.out.println(bookService.getSortedBooks(new BookDateOfWritingComparator()));


    }

}
