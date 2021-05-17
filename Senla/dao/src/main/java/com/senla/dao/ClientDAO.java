package com.senla.dao;

import com.senla.api.dao.IClientDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.dao.util.DataBaseHandler;

import com.senla.model.Client;
import org.apache.logging.log4j.Level;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.*;

@Repository
public class ClientDAO extends AbstractDAO<Client> implements IClientDAO {


    @Override
    public List<Client> getAll() throws DAOException {
        try {
            String sql = "SELECT *  FROM " + Constants.CLIENTS_TABLE;
            Connection connection = DriverManager.getConnection("");

            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<Client> clients = new ArrayList<>();

            while (rs.next()) {
                String name = rs.getString(Constants.CLIENTS_CLIENT_NAME);
                String email = rs.getString(Constants.CLIENTS_CLIENT_EMAIL);

                Client client = new Client(name, email);

                client.setUuid(UUID.fromString(rs.getString(Constants.CLIENTS_CLIENT_ID)));
                clients.add(client);
            }

            rs.close();
            statement.close();

            return clients;
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARN, throwables.getSQLState(), throwables);
            throw new DAOException(String.format("Could not get All Clients from DB : %s", throwables.getCause()), throwables);
        } finally {

        }
    }

    public void update(UUID id, Client item) throws DAOException {
        try {
            String sql = "UPDATE " + Constants.CLIENTS_TABLE +
                    " SET " + Constants.CLIENTS_CLIENT_NAME + " = '" + item.getName() + "' ," +
                    Constants.CLIENTS_CLIENT_EMAIL + "= '" + item.getMail() +
                    "' WHERE " + Constants.CLIENTS_CLIENT_ID + " = '" + id + "' ;";
            PreparedStatement statement = DriverManager.getConnection("").prepareStatement(sql);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DAOException("Client update failed", e);
        }
    }

    public void delete(UUID id) throws DAOException {
        try {
            String sql = "DELETE " + Constants.CLIENTS_TABLE +
                    " WHERE " + Constants.CLIENTS_CLIENT_ID + " = '" + id + "' ;";
            PreparedStatement statement = DriverManager.getConnection("").prepareStatement(sql);

            LOGGER.info(sql);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new DAOException("Client delete failed", e);
        }
    }

    public void delete(Client id) throws DAOException {   // Useless method

        try {
            String sql = "DELETE " + Constants.CLIENTS_TABLE +
                    " WHERE " + Constants.CLIENTS_CLIENT_ID + " = '" + id.getUuid() + "' ;";
            PreparedStatement statement = DriverManager.getConnection("").prepareStatement(sql);

            LOGGER.info(sql);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new DAOException("Client delete failed", e);
        }
    }

    public void addEntity(Client entity) throws DAOException {
        try {
            String sql = "INSERT INTO  " + Constants.CLIENTS_TABLE +
                    " VALUES ('" + entity.getUuid() + "', '" + entity.getName() + "', '" +
                    entity.getMail() + "');";
            PreparedStatement statement = DriverManager.getConnection("").prepareStatement(sql);
            LOGGER.info(sql);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DAOException("Client update failed", e);
        }
    }

    public Client getEntityById(UUID id) throws DAOException {
        try {
            String sql = "SELECT * FROM " + Constants.CLIENTS_TABLE +
                    " WHERE " + Constants.CLIENTS_CLIENT_ID + " = '" + id + "' ;";
            PreparedStatement statement = DriverManager.getConnection("").prepareStatement(sql);
            LOGGER.info(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                UUID uuid =UUID.fromString( rs.getString(Constants.CLIENTS_CLIENT_ID));
                String name = rs.getString(Constants.CLIENTS_CLIENT_NAME);
                String email = rs.getString(Constants.CLIENTS_CLIENT_EMAIL);

                return  new Client(uuid, name, email);
            }

            rs.close();
            statement.close();

            throw new DAOException("No such client in bd");
        } catch (SQLException e) {
            throw new DAOException("Client delete failed", e);
        }
    }

    @Override
    protected Client getEntity(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected String getAllEntitiesQuerySQL() {
        return null;
    }

    @Override
    protected Class<Client> getClazz() {
        return null;
    }


    public ClientDAO(){
        super();
    }


}
