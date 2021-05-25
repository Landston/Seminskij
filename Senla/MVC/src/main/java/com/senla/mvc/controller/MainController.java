package com.senla.mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {



    @GetMapping("/home")
    public String home(Model model) {

        return "home";
    }

    @GetMapping("/")
    public String startPage(Model model) {

        return "startPage";
    }


}
