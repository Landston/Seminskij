package com.senla.dao;

import com.senla.api.dao.IBookDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.dao.util.DataBaseHandler;
import com.senla.model.Book;
import com.senla.model.BookStatus;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDAO extends AbstractDAO<Book> implements IBookDAO {

    private static final String GET_ALL_QUERY = "SELECT * FROM " + Constants.BOOKS_TABLE + " ;";
    private static final String UPDATE_QUERY = "UPDATE " + Constants.BOOKS_TABLE +
            " SET " + Constants.BOOKS_GENRE + " =? , "
            + Constants.BOOKS_NAME + " =? , "
            + Constants.BOOKS_YEAR + " =? , "
            + Constants.BOOKS_COST + " =? , "
            + Constants.BOOKS_DATE_OF_ADMISSION + " = ? , "
            + Constants.BOOKS_BOOK_STATUS + " =? , "
            + Constants.BOOKS_BOOK_ID + " =? "
            + " WHERE " + Constants.BOOKS_BOOK_ID + " = ? ;";
    private final String DELETE_QUERY = "DELETE FROM " + Constants.BOOKS_TABLE
            + " WHERE " + Constants.BOOKS_BOOK_ID + " = ? ;";
    private final String INSERT_QUERY = "INSERT INTO " + Constants.BOOKS_TABLE +
            " VALUES ( ?, ?, ?, ?, ?, ?, ?);";
    private final String GET_BY_ID_QUERY = "SELECT * FROM " + Constants.BOOKS_TABLE +
            " WHERE " + Constants.BOOKS_BOOK_ID + " = ? ;";

    public BookDAO(DataBaseHandler dataBaseHandler) {
        super();
    }

    public BookDAO(){

    }

    @Override
    public Book getEntity(ResultSet rs) throws SQLException {

        UUID book_id = rs.getObject(Constants.BOOKS_BOOK_ID, UUID.class);
        String genre = rs.getString(Constants.BOOKS_GENRE);
        String name = rs.getString(Constants.BOOKS_NAME);
        int year = rs.getInt(Constants.BOOKS_YEAR);
        Double cost = rs.getDouble(Constants.BOOKS_COST);
        Date date = rs.getDate(Constants.BOOKS_DATE_OF_ADMISSION);
        String status = rs.getString(Constants.BOOKS_BOOK_STATUS);
        Book book = new Book(book_id, name, genre, year, cost, BookStatus.valueOf(status));

        book.setDateOfAdmission(date.toLocalDate());
        book.setUuid(book_id);

        return book;
    }

    @Override
    protected String getAllEntitiesQuerySQL() {
        return null;
    }

    @Override
    protected Class<Book> getClazz() {
        return Book.class;
    }

    @Override
    public List<Book> getAll() throws DAOException {
        return null;
    }

   /* @Override
    public List<Book> getAll() throws DAOException {

        List<Book> books = new ArrayList<>();
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet rs = statement.executeQuery()) {
            connection.setAutoCommit(false);

            while (rs.next()) {
                UUID book_id = rs.getObject(Constants.BOOKS_BOOK_ID, UUID.class);
                String genre = rs.getString(Constants.BOOKS_GENRE);
                String name = rs.getString(Constants.BOOKS_NAME);
                int year = rs.getInt(Constants.BOOKS_YEAR);
                Double cost = rs.getBigDecimal(Constants.BOOKS_COST).doubleValue();
                String status = rs.getString(Constants.BOOKS_BOOK_STATUS);
                Date date = rs.getDate(Constants.BOOKS_DATE_OF_ADMISSION);
                Book book = new Book(book_id, name, genre, year, cost, BookStatus.valueOf(status));

                book.setDateOfAdmission(date.toLocalDate());
                books.add(book);
            }
            return books;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new DAOException("BookDAO opeartion failed");

    }*/

  /*  @Override
    public void update(UUID id, Book item) throws DAOException {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, item.getGenre());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getYear());
            preparedStatement.setDouble(4, item.getCost());
            preparedStatement.setDate(5, Date.valueOf(item.getDateOfAdmission()));
            preparedStatement.setString(6, item.getStatus().toString());
            preparedStatement.setObject(7, item.getUuid());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, e.getSQLState(), e);
            throw new DAOException("UPDATE_BOOK_EXCEPTION", e);
        }
    }

    @Override
    public void delete(UUID id) throws DAOException {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("DELETE_BOOK_EXCEPTION", e);
        }
    }

    @Override
    public void delete(Book id) throws DAOException {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("DELETE_BOOK_EXCEPTION", e);
        }
    }


   */

/*
    private void prepareStatementForAddEntity(Book entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    public Book getEntityById(UUID id) throws DAOException {

        LOGGER.info(GET_BY_ID_QUERY);
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                UUID book_id = rs.getObject(Constants.BOOKS_BOOK_ID, UUID.class);
                String genre = rs.getString(Constants.BOOKS_GENRE);
                String name = rs.getString(Constants.BOOKS_NAME);
                int year = rs.getInt(Constants.BOOKS_YEAR);
                Double cost = rs.getDouble(Constants.BOOKS_COST);
                Date date = rs.getDate(Constants.BOOKS_DATE_OF_ADMISSION);
                String status = rs.getString(Constants.BOOKS_BOOK_STATUS);
                Book book = new Book(book_id, name, genre, year, cost, BookStatus.valueOf(status));

                book.setDateOfAdmission(date.toLocalDate());
                book.setUuid(book_id);

                return book;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new DAOException("GET_BOOK_BY_ID_EXCEPTION");

    }
    public Book getEntity(ResultSet rs) throws SQLException {

        UUID book_id = rs.getObject(Constants.BOOKS_BOOK_ID, UUID.class);
        String genre = rs.getString(Constants.BOOKS_GENRE);
        String name = rs.getString(Constants.BOOKS_NAME);
        int year = rs.getInt(Constants.BOOKS_YEAR);
        Double cost = rs.getDouble(Constants.BOOKS_COST);
        Date date = rs.getDate(Constants.BOOKS_DATE_OF_ADMISSION);
        String status = rs.getString(Constants.BOOKS_BOOK_STATUS);
        Book book = new Book(book_id, name, genre, year, cost, BookStatus.valueOf(status));

        book.setDateOfAdmission(date.toLocalDate());
        book.setUuid(book_id);

        return book;
    }
    @Override
    protected String getAllEntitiesQuerySQL() {
        return GET_ALL_QUERY;
    }



/*    protected String getAllEntitiesQuerySQL() {
        return null;
    }*/
}



