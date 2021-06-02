package com.senla.api.dao;


import com.senla.api.exception.service.DAOException;
import com.senla.model.Request;

import java.util.UUID;

public interface IRequestDAO extends IDAO<Request> {

    Request requestByBookId(UUID id) throws DAOException;
}
