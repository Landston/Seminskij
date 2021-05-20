package com.senla.controller;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.api.service.IClientService;
import com.senla.api.service.IOrderService;
import com.senla.controller.config.Config;
import com.senla.model.Book;
import com.senla.model.BookStatus;
import com.senla.model.Client;
import com.senla.model.Order;
import com.senla.ui.MenuController;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.UUID;


public class MainController {



    public static void main(String[] args) throws DAOException, ServiceException {

        Logger log = LogManager.getLogger(MainController.class.getName());

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);


//        IBookService bookService = applicationContext.getBean("bookService", IBookService.class);
//        IClientService clientService = applicationContext.getBean("clientService", IClientService.class);
//
//       // Client client = new Client("Zigmund freyd", "CocainLover@gmail.com");
//        //clientService.add(client);
//        IOrderService orderService = applicationContext.getBean("orderService", IOrderService.class);
////
////        Client orderClient = clientService.getAll().get(0);
////
//        List<Book> books = bookService.getAll();
//
//
//        orderService.createOrder(books.get(8).getId(), orderClient.getId());

//        System.out.println(orderService.getAll());
       // Book book = service.getBookById(UUID.fromString("9bd3ec21-4b2f-43ac-b2d3-93141e84695f"));

        MenuController menuController = applicationContext.getBean("menuController", MenuController.class);
        menuController.run();
        
        System.out.println();
    }
}
