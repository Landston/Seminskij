package com.senla.model.api.dao;

import com.senla.di.annotation.Singleton;
import com.senla.model.exception.DAOException;
import com.senla.model.model.Book;
import com.senla.model.model.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderDAO extends IDAO<Order> {

    void delete(Order order) throws DAOException;

    List<Book> getBooksForOrder(UUID order_id);

}
