package com.senla.model.api.dao;

import com.senla.model.models.Order;

public interface IOrderDAO extends IDAO<Order> {

    void delete(Order order);


}
