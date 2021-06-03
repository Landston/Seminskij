package com.senla.controller.controller;

import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.model.dto.BookDTO;
import com.senla.ui.actions.request.RequestSortAction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/books")
@Log4j2
public class BookController {

    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<BookDTO>> allBooks() throws ServiceException {
        System.out.println();

        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id) throws ServiceException {
        BookDTO bookDto =  bookService.getBookById(UUID.fromString(id));

        return ResponseEntity.ok(bookDto);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<BookDTO> deleteBookById(@RequestParam String id) throws ServiceException {
        bookService.delete(UUID.fromString(id));

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping(value = "/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) throws ServiceException {
        BookDTO newBookDto = bookService.add(bookDTO);

        return ResponseEntity.ok(newBookDto);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) throws ServiceException {
        bookService.update(bookDTO);

        return ResponseEntity.ok().build();
    }


}
