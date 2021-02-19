package com.Models.api.DAO;

import com.Models.Models.Order;

public interface IOrderDAO extends IDAO<Order> {

    void delete(Order order);


}
