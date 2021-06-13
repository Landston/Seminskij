package com.senla.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IUserService;
import com.senla.controller.config.Config;
import com.senla.dao.AbstractDAO;
import com.senla.model.Role;
import com.senla.model.User;
import com.senla.service.UserService;
import com.senla.ui.MenuController;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Log4j2
public class MainController {

    public void doSomething() {


    }

    public static void main(String[] args) throws DAOException, ServiceException, JsonProcessingException {

        Logger log = LogManager.getLogger(MainController.class.getName());
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        IUserService userService = applicationContext.getBean("userService", IUserService.class);

        System.out.println(userService.getByLogin("2"));


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

//            MenuController menuController = applicationContext.getBean("menuController", MenuController.class);
//            menuController.run();
//
//            System.out.println();
    }
}
