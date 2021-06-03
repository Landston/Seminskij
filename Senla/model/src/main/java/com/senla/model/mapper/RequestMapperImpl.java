package com.senla.model.mapper;

import com.senla.model.Request;
import com.senla.model.dto.RequestDTO;
import com.senla.model.mapper.api.BookMapper;
import com.senla.model.mapper.api.MapperForRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestMapperImpl implements MapperForRequest {


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

    @Override
    public List<RequestDTO> bunchRequestToRequestDTO(List<Request> requests) {
        return requests.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Request> bunchDtoRequestToRequest(List<RequestDTO> dtoRequests) {
        return dtoRequests.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
