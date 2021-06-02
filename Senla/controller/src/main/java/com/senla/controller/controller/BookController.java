package com.senla.controller.controller;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.dto.BookDTO;
import com.senla.model.mapper.api.BookMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class BookController {

    private final IBookService bookService;

    private final BookMapper bookMapper;

    @Autowired
    public BookController(IBookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<BookDTO>> allBooks() throws ServiceException {
        System.out.println();

        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping(value = "/{id}")
    public Book getBookById(@PathVariable String id) throws ServiceException {
        Book book =  bookService.getBookById(UUID.fromString(id));



    }

}
