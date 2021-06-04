package com.senla.controller.controller;


import com.senla.api.dao.IOrderDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.model.Order;
import com.senla.model.dto.BookDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class MainController {

    @GetMapping("/")
    public String startPage() {
        return "startPage";
    }


}
