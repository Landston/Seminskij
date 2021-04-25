package com.senla.model.dao.util;


import com.senla.di.annotation.ConfigProperty;
import com.senla.di.annotation.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class DataBaseHandler {

    private String USER = "postgres";
    private String PASSWORD = "root";
    private String URL = "jdbc:postgresql://localhost:5432/bookshop";


    private static final Logger LOGGER = Logger.getLogger(DataBaseHandler.class.getName());

    private Connection connection;

    private boolean connectionSetted = false;

    public DataBaseHandler() {
    }

    private void setConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                this.connection = connection;

            }
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, throwables.getStackTrace().toString(), throwables);
            System.out.println("");
        }
    }

    public Connection getConnection() {
        if (connectionSetted == false) {
            setConnection();
        }
        return connection;
    }
}
