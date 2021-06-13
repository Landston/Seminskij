package com.senla.api.dao;


import com.senla.model.Role;
import com.senla.model.User;

import java.util.List;

public interface IUserDAO extends IDAO<User> {

    User getByLogin(String login);

    User loadUserByUsername(String username);
}

