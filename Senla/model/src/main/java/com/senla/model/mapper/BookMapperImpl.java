package com.senla.model.mapper;

import com.senla.model.Book;
import com.senla.model.dto.BookDTO;
import com.senla.model.mapper.api.BookMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDto(Book entity) {
        return new BookDTO(
                entity.getId(),
                entity.getName(),
                entity.getStatus(),
                entity.getGenre(),
                entity.getCost(),
                entity.getYear(),
                entity.getDateOfAdmission()
        );
    }

    @Override
    public Book toEntity(BookDTO dto) {
        return new Book(
                dto.getId(),
                dto.getName(),
                dto.getGenre(),
                dto.getYear(),
                dto.getCost(),
                dto.getStatus()
        );
    }

    @Override
    public List<BookDTO> bookListToBookDTOList(List<Book> books) {
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Book> bookDTOListToBookList(List<BookDTO> books){
        return books.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
