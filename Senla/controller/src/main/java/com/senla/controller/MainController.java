package com.senla.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.senla.api.dao.IBookDAO;
import com.senla.api.dao.IOrderDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.controller.config.Config;
import com.senla.dao.BookDAO;
import com.senla.dao.OrderDAO;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.ui.MenuController;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

@Log4j2
public class MainController {

    public void doSomething()
    {


    }
    public static void main(String[] args) throws DAOException, ServiceException, JsonProcessingException {

            Logger log = LogManager.getLogger(MainController.class.getName());
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        IOrderDAO dao = applicationContext.getBean("orderDAO", OrderDAO.class);
        Order book = dao.getEntityById(UUID.fromString("9e64219e-ae13-4856-8fd9-e8cb31754ea3"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        System.out.println(book);
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
