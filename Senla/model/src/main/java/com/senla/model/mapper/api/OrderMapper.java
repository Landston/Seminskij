package com.senla.model.mapper.api;

import com.senla.model.Order;
import com.senla.model.dto.OrderDTO;

import java.util.List;

public interface OrderMapper extends Mapper<Order, OrderDTO> {

    public List<OrderDTO> orderBunchToOrderDTOBunch(List<Order> orders);

    public List<Order> orderDtoBunchToOrder(List<OrderDTO> orders);

    Order orderDtoToOrder(Order order, OrderDTO dto);
}
