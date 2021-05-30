package com.senla.start.mvc.controller;


import com.senla.api.dao.IBookDAO;
import com.senla.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
public class Controller {


    private final IBookDAO dao;

    @Autowired
    public Controller(IBookDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/home")
    public String home(Model model) {
    Book book = new Book();
    model.addAttribute(book);


        return "home";
    }

    @GetMapping("/")
    public String startPage(Model model) {
        return "startPage";
    }


}
