package com.senla.service;

import com.senla.api.dao.IUserDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.service.IUserService;
import com.senla.model.Role;
import com.senla.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserDAO userDAO;

    @Override
    public void save(User user) throws DAOException {
        userDAO.addEntity(user);
    }

    @Override
    public User getByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    public User getById(UUID id) throws DAOException {
        return userDAO.getEntityById(id);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.loadUserByUsername(username);
    }
}
