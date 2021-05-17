package com.senla.dao;

import com.senla.api.dao.IRequestDAO;
import com.senla.api.exception.service.DAOException;

import com.senla.dao.util.DataBaseHandler;

import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.BookStatus;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class RequestDAO extends AbstractDAO<Request> implements IRequestDAO {

    public RequestDAO(){
        super();
    }
    @Override
    public List<Request> getAll() throws DAOException {
        String sql = "SELECT * FROM " + Constants.REQUESTS_TABLE + " as rq "
                + " INNER JOIN " + Constants.BOOKS_TABLE + " as bk ON bk." + Constants.BOOKS_BOOK_ID + " = rq." + Constants.REQUESTS_BOOK_ID + " ;";
        List<Request> requests = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("");


            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Request request = getRequestFromResultSet(resultSet);

                requests.add(request);

            }

            preparedStatement.close();
        } catch (SQLException e) {
            throw new DAOException("GET_ALL_REQUEST_EXCEPTION", e);
        }
        return requests;
    }

    @Override
    public void update(UUID id, Request item) throws DAOException {
        String sql = "UPDATE " + Constants.REQUESTS_TABLE +
                " SET " + Constants.REQUESTS_REQUEST_ID + " = ?,"
                + Constants.REQUESTS_BOOK_ID + " = ?,"
                + Constants.REQUESTS_COUNT + " = ?,"
                + Constants.REQUESTS_OPEN_CLOSE + " = ?"
                + " WHERE " + Constants.REQUESTS_REQUEST_ID + " = ? ;";

        try {
            Connection connection = DriverManager.getConnection("");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, item.getRequestedBook().getUuid());
            preparedStatement.setInt(3, item.getCount());
            preparedStatement.setBoolean(4, item.getRequestOpenClose());
            preparedStatement.setObject(5, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            new DAOException("REQUEST_UPDATE_EXCEPTION", e);
        }
    }

    @Override
    public void delete(UUID id) throws DAOException {
        String sql = "DELETE FROM " + Constants.REQUESTS_TABLE + " AS rq "
                + " WHERE rq." + Constants.REQUESTS_REQUEST_ID + " = ? ;";

        try {
            Connection connection = DriverManager.getConnection("");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            new DAOException("REQUEST_DELETE_EXCEPTION", e);
        }
    }

    @Override
    public void delete(Request entity) throws DAOException {
        String sql = "DELETE FROM " + Constants.REQUESTS_TABLE + " AS rq "
                + " WHERE rq." + Constants.REQUESTS_REQUEST_ID + " = ? ;";

        try {
            Connection connection = DriverManager.getConnection("");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, entity.getUuid());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            new DAOException("REQUEST_DELETE_EXCEPTION", e);
        }
    }

    @Override
    public void addEntity(Request entity) throws DAOException {
        String sql = "INSERT INTO " + Constants.REQUESTS_TABLE
             +" VALUES (?, ?, ?, ?);";

        try {
            Connection connection = DriverManager.getConnection("");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, entity.getUuid());
            preparedStatement.setObject(2, entity.getRequestedBook().getUuid());
            preparedStatement.setInt(3, entity.getCount());
            preparedStatement.setBoolean(4, entity.getRequestOpenClose());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            new DAOException("REQUEST_ADD_EXCEPTION", e);
        }
    }

    @Override
    public Request getEntityById(UUID id) throws DAOException {
        String sql = "SELECT * FROM " + Constants.REQUESTS_TABLE + " as rq "
                + " INNER JOIN " + Constants.BOOKS_TABLE + " as bk ON bk." + Constants.BOOKS_BOOK_ID + " = rq." + Constants.REQUESTS_BOOK_ID
                + " WHERE  rq." + Constants.REQUESTS_REQUEST_ID + " = ? ;";


        try {
            Connection connection = DriverManager.getConnection("");


            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Request request = getRequestFromResultSet(resultSet);

                preparedStatement.close();
                return request;
            }

            preparedStatement.close();
        } catch (SQLException e) {
            throw new DAOException("GET_ALL_REQUEST_EXCEPTION", e);
        }
        throw new DAOException("No Such Request");
    }

    @Override
    protected Request getEntity(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected String getAllEntitiesQuerySQL() {
        return null;
    }

    @Override
    protected Class<Request> getClazz() {
        return null;
    }

    private Request getRequestFromResultSet(ResultSet resultSet) throws SQLException {
        UUID request_id = resultSet.getObject(Constants.REQUESTS_REQUEST_ID, UUID.class);
        int count = resultSet.getInt(Constants.REQUESTS_COUNT);
        boolean openClose = resultSet.getBoolean(Constants.REQUESTS_OPEN_CLOSE);

        UUID book_id = resultSet.getObject(Constants.BOOKS_BOOK_ID, UUID.class);
        String genre = resultSet.getString(Constants.BOOKS_GENRE);
        String name = resultSet.getString(Constants.BOOKS_NAME);
        int year = resultSet.getInt(Constants.BOOKS_YEAR);
        double cost = resultSet.getBigDecimal(Constants.BOOKS_COST).doubleValue();
        String status = resultSet.getString(Constants.BOOKS_BOOK_STATUS);
        Date date = resultSet.getDate(Constants.BOOKS_DATE_OF_ADMISSION);
        Book book = new Book(book_id, name, genre, year, cost, BookStatus.valueOf(status));

        Request request = new Request(request_id, book, count, openClose);

        return request;

    }
}

