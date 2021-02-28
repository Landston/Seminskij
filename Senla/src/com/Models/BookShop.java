package com.Models;

import com.Models.DAO.BookDAO;
import com.Models.DAO.ClientDAO;
import com.Models.DAO.OrderDAO;
import com.Models.DAO.RequestDAO;
import com.Models.Services.BookService;
import com.Models.Services.ClientService;
import com.Models.Services.OrderService;
import com.Models.Services.RequestService;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;


public class BookShop {

   private static BookDAO BOOK_DAO =  BookDAO.getInstance();
   private static OrderDAO ORDER_DAO = OrderDAO.getInstance();
   private static RequestDAO REQUEST_DAO = RequestDAO.getInstance();
   private static ClientDAO CLIENT_DAO=  ClientDAO.getInstance();
   private static  ClientService clientService = ClientService.getInstance();
   private static BookService bookService = BookService.getInstance();
   private static OrderService orderService =  OrderService.getInstance();
   private static RequestService requestService = RequestService.getInstance();



    public static void main(String[] args)  {









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
