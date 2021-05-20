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
    protected Class<Client> getClazz() {
        return Client.class;
    }


}
