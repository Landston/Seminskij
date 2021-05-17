package com.senla.dao.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DataBaseHandler {

    private String USER = "postgres";
    private String PASSWORD = "root";
    private String URL = "jdbc:postgresql://localhost:5432/bookshop";


    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(DataBaseHandler.class);

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
            LOGGER.log(Level.WARN, throwables.getStackTrace().toString(), throwables);
            System.out.println("");
        }
    }

    public Connection getConnection() {
        if (!connectionSetted) {
            setConnection();
        }
        return connection;
    }
}
