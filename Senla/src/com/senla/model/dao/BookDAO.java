package com.senla.model.dao;

import com.senla.di.annotation.Singleton;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.model.configuration.DataBaseHandler;
import com.senla.model.exception.DAOException;
import com.senla.model.model.Book;
import com.senla.model.api.dao.IBookDAO;
import com.senla.model.model.BookStatus;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class BookDAO extends AbstractDAO<Book> implements IBookDAO {

    private static final Logger LOGGER = Logger.getLogger(BookDAO.class.getName());


    public BookDAO() {

    }

    @Override
    public List<Book> getAll() throws DAOException {
        String sql = "SELECT * FROM " + Constants.BOOKS_TABLE + " ;";
        List<Book> books = new ArrayList<>();
        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();


        try (PreparedStatement statement = connection.prepareStatement(sql);
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

    }

    @Override
    public void update(UUID id, Book item) throws DAOException {
        String sql = "UPDATE " + Constants.BOOKS_TABLE +
                " SET " + Constants.BOOKS_GENRE + " = '" + item.getGenre() + ", "
                + Constants.BOOKS_NAME + " = '" + item.getName() + "' , "
                + Constants.BOOKS_YEAR + " = " + item.getYear() + " , "
                + Constants.BOOKS_COST + " = " + item.getCost() + " , "
                + Constants.BOOKS_DATE_OF_ADMISSION + " = '" + Date.valueOf(item.getDateOfAdmission()) + "' , "
                + Constants.BOOKS_BOOK_STATUS + " = '" + item.getStatus() + "' , "
                + Constants.BOOKS_BOOK_ID + " = '" + item.getUuid() + "' , "
                + " WHERE " + Constants.BOOKS_BOOK_ID + " = '" + id + "' ;";

        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getSQLState(), e);
           throw new DAOException("UPDATE_BOOK_EXCEPTION", e);
        }
    }

    @Override
    public void delete(UUID id) throws DAOException {
        String sql = "DELETE FROM " + Constants.BOOKS_TABLE
                + " WHERE " + Constants.BOOKS_BOOK_ID + " = '" + id + "' ;";

        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           throw new DAOException("DELETE_BOOK_EXCEPTION", e);
        }
    }

    @Override
    public void delete(Book id) throws DAOException {
        String sql = "DELETE " + Constants.BOOKS_TABLE
                + " WHERE " + Constants.BOOKS_BOOK_ID + " = '" + id.getUuid() + "' ;";

        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           throw  new DAOException("DELETE_BOOK_EXCEPTION", e);
        }
    }

    @Override
    public void addEntity(Book entity) throws DAOException {
        String sql = "INSERT INTO " + Constants.BOOKS_TABLE +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?);";

        try {
            Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
            LOGGER.info(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getGenre());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getYear());
            preparedStatement.setDouble(4, entity.getCost());
            preparedStatement.setDate(5, Date.valueOf(entity.getDateOfAdmission()));
            preparedStatement.setString(6, entity.getStatus().toString());
            preparedStatement.setObject(7, entity.getUuid());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ADD_BOOK_EXCEPTION");

        }

    }

    @Override
    public Book getEntityById(UUID id) throws DAOException {
        String sql = "SELECT * FROM " + Constants.BOOKS_TABLE +
                " WHERE " + Constants.BOOKS_BOOK_ID + " = '" + id + "' ;";
        LOGGER.info(sql);
        Connection connection = ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql);
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
}



