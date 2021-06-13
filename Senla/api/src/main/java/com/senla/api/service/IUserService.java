package com.senla.api.service;



import com.senla.api.exception.service.DAOException;
import com.senla.model.Role;
import com.senla.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface IUserService extends UserDetailsService {

    void save(User user) throws DAOException;

    User getByLogin(String login);

    User getById(UUID id) throws DAOException;

}
