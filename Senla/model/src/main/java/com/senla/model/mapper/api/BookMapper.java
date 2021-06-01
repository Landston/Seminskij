package com.senla.model.mapper.api;

import com.senla.model.Book;
import com.senla.model.dto.BookDTO;

import java.util.List;
import java.util.UUID;

public interface BookMapper extends Mapper<Book, BookDTO> {

    List<BookDTO> bookListToBookDTOList(List<Book> books);

    List<Book> bookDTOListToBookList(List<BookDTO> books);
}
