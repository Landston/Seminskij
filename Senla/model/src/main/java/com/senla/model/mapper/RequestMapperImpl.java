package com.senla.model.mapper;

import com.senla.model.Request;
import com.senla.model.dto.RequestDTO;
import com.senla.model.mapper.api.BookMapper;
import com.senla.model.mapper.api.RequestMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public class RequestMapperImpl implements RequestMapper {


    private final BookMapper bookMapper;


    public RequestMapperImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public RequestDTO toDto(Request entity) {
        return new RequestDTO(
                entity.getId(),
                bookMapper.toDto(entity.getRequestedBooks()),
                entity.getCount(),
                entity.isRequestOpenClose()
        );
    }

    @Override
    public Request toEntity(RequestDTO dto) {
        return new Request(
                dto.getId(),
                bookMapper.toEntity(dto.getRequestedBooks()),
                dto.getCount(),
                dto.isRequestOpenClose()
        );
    }
}
