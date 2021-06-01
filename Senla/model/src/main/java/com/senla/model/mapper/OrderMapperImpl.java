package com.senla.model.mapper;

import com.senla.model.Order;
import com.senla.model.dto.OrderDTO;

import com.senla.model.mapper.api.BookMapper;
import com.senla.model.mapper.api.ClientMapper;
import com.senla.model.mapper.api.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public OrderDTO toDto(Order entity) {
        return new OrderDTO(
                entity.getId(),
                bookMapper.bookListToBookDTOList(entity.getBooksToOrder()),
                clientMapper.toDto(entity.getClient()),
                entity.getStatus(),
                entity.getTotalPrice(),
                entity.getDateOfExecution()
        );
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        return new Order(
                dto.getId(),
                bookMapper.bookDTOListToBookList(dto.getBooksToOrder()),
                clientMapper.toEntity(dto.getClient()),
                dto.getStatus(),
                dto.getTotalPrice(),
                dto.getDateOfExecution()
        );
    }
}
