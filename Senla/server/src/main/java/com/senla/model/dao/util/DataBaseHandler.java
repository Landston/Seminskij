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
    @ConfigProperty(configName = "D:\\Senla\\proj\\senla\\server\\src\\main\\java\\com\\senla\\model\\resource\\jdbcConfig.properties", propertyName = "USER")
    private  String USER;
    @ConfigProperty(configName = "D:\\Senla\\proj\\senla\\server\\src\\main\\java\\com\\senla\\model\\resource\\jdbcConfig.properties", propertyName = "PASS")
    private  String PASSWORD;
    @ConfigProperty(configName = "D:\\Senla\\proj\\senla\\server\\src\\main\\java\\com\\senla\\model\\resource\\jdbcConfig.properties", propertyName = "URL")
    private  String URL;



    private static final Logger LOGGER = Logger.getLogger(DataBaseHandler.class.getName());

    private Connection connection;

    private boolean connectionSetted = false;

    public DataBaseHandler() {
    }

    private void setConnection(){
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
        if(connectionSetted == false){
            setConnection();
        }
        return connection;
    }
}
