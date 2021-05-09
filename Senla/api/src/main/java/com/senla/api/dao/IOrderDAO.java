package com.senla.api.dao;



import com.senla.api.exception.service.DAOException;
import com.senla.model.Book;
import com.senla.model.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderDAO extends IDAO<Order> {

    void delete(Order order) throws DAOException;

    List<Book> getBooksForOrder(UUID order_id);

}
