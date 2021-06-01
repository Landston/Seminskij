package com.senla.controller.controller;


import com.senla.api.dao.IOrderDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.model.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Log4j2
public class Controller {


    private final IOrderDAO dao;

    @Autowired
    public Controller(IOrderDAO dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/home/book/{id}")
    public Order home(Model model, @PathVariable String id) {
        try {
            Order order = dao.getEntityById(UUID.fromString(id));

//            OrderDTO dto = new OrderDTO();
//            System.out.println(order);
//        Client client = order.getClient();
//      //      dto.setBooksToOrder(order.getBooksToOrder());
//           dto.setClient(client);
//            dto.setId(order.getId());
//            dto.setDateOfExecution(order.getDateOfExecution());
            return order;
        } catch (DAOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping("/")
    public String startPage(Model model) {
        return "startPage";
    }


}
