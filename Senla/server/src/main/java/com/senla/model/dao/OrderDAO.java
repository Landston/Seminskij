package com.senla.model.dao;


import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.model.dao.util.DataBaseHandler;
import com.senla.model.exception.DAOException;
import com.senla.model.model.*;
import com.senla.model.api.dao.IOrderDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

@Singleton
public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

    public OrderDAO(){
        super();
    }

    @Override
    public List<Order> getAll() throws DAOException {
        String sql = "SELECT * FROM " + Constants.ORDERS_TABLE + " as ord "
                + " INNER JOIN " + Constants.CLIENTS_TABLE + " as cl ON " + "ord." + Constants.ORDERS_ORDER_CLIENT_ID + " = " + "cl." + Constants.CLIENTS_CLIENT_ID + " ;";
        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
        List<Order> orders = new ArrayList<>();

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFor(resultSet);

                orders.add(order);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getSQLState(), e);
            throw new DAOException("GET_ALL_ORDERDAO_EXCEPTION", e);
        }
        return orders;
    }


    private Order getOrderFor(ResultSet resultSet) throws SQLException {
        UUID order_id = UUID.fromString(resultSet.getString(Constants.ORDERS_ID));
        double total_price = resultSet.getBigDecimal(Constants.ORDERS_TOTAL_PRICE).doubleValue();
        LocalDate date = resultSet.getDate(Constants.ORDERS_DATE).toLocalDate();
        String status = resultSet.getString(Constants.ORDERS_ORDER_STATUS);

        UUID client_id = UUID.fromString(resultSet.getString(Constants.ORDERS_ORDER_CLIENT_ID));
        String name = resultSet.getString(Constants.CLIENTS_CLIENT_NAME);
        String email = resultSet.getString(Constants.CLIENTS_CLIENT_EMAIL);
        Client client = new Client(client_id, name, email);

        Order order = new Order(order_id, client, OrderStatus.valueOf(status), total_price, date);

        order.setBooksToOrder(getBooksForOrder(order_id));

        return order;
    }


    @Override
    public void update(UUID id, Order item) throws DAOException {
        String sql = "UPDATE " + Constants.ORDERS_TABLE +
                " SET " + Constants.ORDERS_ID + "= ? ,"
                + Constants.ORDERS_TOTAL_PRICE + "= ? ,"
                + Constants.ORDERS_DATE + "= ? ,"
                + Constants.ORDERS_ORDER_STATUS + "= ? ,"
                + Constants.ORDERS_ORDER_CLIENT_ID + "= ? "
                + "WHERE " + Constants.ORDERS_ID + "= ? ;";

        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);

            pr.setObject(1, id);
            pr.setDouble(2, item.getTotalPrice());
            pr.setDate(3, Date.valueOf(item.getDateOfExecution()));
            pr.setString(4, item.getStatus().name());
            pr.setObject(5, item.getClient().getUuid());

            pr.executeUpdate();

            pr.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("UPDATE_ERROR", id));
            throw new DAOException(String.format("UPDATE_ERROR", id));
        }
    }

    @Override
    public void delete(UUID id) throws DAOException {
        String sql = "DELETE FROM " + Constants.ORDERS_TABLE +
                " WHERE " + Constants.ORDERS_ID + " = ? ;";
        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);

            pr.setObject(1, id);

            pr.executeUpdate();

            pr.close();

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, String.format("DELETE_ORDER_ERROR", id));
            throw new DAOException("DELETE_ERROR");

        }
    }

    public void delete(Order entity) throws DAOException {
        throw new DAOException(" ");
    }

    @Override
    public List<Book> getBooksForOrder(UUID order_id) {
        String sql = "SELECT * " + " FROM " + Constants.ORDER_SPECIFICATION_TABLE + " as ors "
                + "INNER JOIN " + Constants.BOOKS_TABLE + " as bk ON bk." + Constants.BOOKS_BOOK_ID + " = ors." + Constants.ORDER_SPECIFICATION_BOOK_ID
                + " WHERE ors." + Constants.ORDER_SPECIFICATION_ORDER_ID + " = " + " ? ;";
        List<Book> books = new ArrayList<>();
        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();


        try {
            PreparedStatement pr = connection.prepareStatement(sql);

            connection.setAutoCommit(false);
            pr.setObject(1, order_id);

            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                UUID id = rs.getObject(Constants.BOOKS_BOOK_ID, UUID.class);
                String genre = rs.getString(Constants.BOOKS_GENRE);
                String name = rs.getString(Constants.BOOKS_NAME);
                int year = rs.getInt(Constants.BOOKS_YEAR);
                Double cost = rs.getBigDecimal(Constants.BOOKS_COST).doubleValue();
                String status = rs.getString(Constants.BOOKS_BOOK_STATUS);
                Date date = rs.getDate(Constants.BOOKS_DATE_OF_ADMISSION);
                Book book = new Book(name, genre, year, cost, BookStatus.valueOf(status));

                book.setDateOfAdmission(date.toLocalDate());
                book.setUuid(id);
                books.add(book);

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getSQLState(), e);
        }
        return books;
    }

    @Override
    public void addEntity(Order entity) throws DAOException {
        try {
            String sql = "INSERT INTO " + Constants.ORDERS_TABLE +
                    " VALUES (?, ?, ?, ?, ?);";

            Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
            PreparedStatement pr = connection.prepareStatement(sql);

            pr.setObject(1, entity.getUuid());
            pr.setDouble(2, entity.getTotalPrice());
            pr.setDate(3, Date.valueOf(entity.getDateOfExecution()));
            pr.setString(4, entity.getStatus().name());
            pr.setObject(5, entity.getClient().getUuid());

            pr.executeUpdate();

            pr.close();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("ADD_ENTITY_ERROR", entity));
            throw new DAOException("ADD_ENTITY_ERROR");
        }
    }

    @Override
    public Order getEntityById(UUID id) throws DAOException {
        String sql = "SELECT * FROM " + Constants.ORDERS_TABLE + " ord "
                + " INNER JOIN " + Constants.CLIENTS_TABLE + " as cl ON " + "ord." + Constants.ORDERS_ORDER_CLIENT_ID + " = " + "cl." + Constants.CLIENTS_CLIENT_ID
                + " WHERE ord." + Constants.ORDERS_ID + "=? ;";

        Order order = new Order();

        try {
            Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                order = getOrderFor(resultSet);

                preparedStatement.close();

                return order;
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, String.format("GET_ENTITY_BY_ID_ERROR", id));
            throw new DAOException("GET_ENTITY_BY_ID_ERROR", e);
        }
        return order;
    }

    @Override
    protected Order getEntity(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected String getAllEntitiesQuerySQL() {
        return null;
    }


}
