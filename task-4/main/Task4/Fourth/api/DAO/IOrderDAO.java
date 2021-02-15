package main.Task3.Fourth.api.DAO;

import main.Task3.Fourth.Models.Order;

import java.util.UUID;

public interface IOrderDAO extends IDAO<Order> {

    void delete(Order order);


}
