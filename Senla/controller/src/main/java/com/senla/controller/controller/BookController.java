package com.senla.controller.controller;

import com.senla.api.service.IBookService;
import com.senla.model.dto.BookDTO;
import com.senla.model.mapper.api.BookMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/all")
@Log4j2
public class BookController {

    private final IBookService bookService;

    private final BookMapper bookMapper;

    @Autowired
    public BookController(IBookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping(name = "/all")
    public ResponseEntity<List<BookDTO>> allBooks(){
        return bookService.getAll();

    }

}
