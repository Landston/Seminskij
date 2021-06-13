package com.senla.dao;

import com.senla.api.dao.IUserDAO;
import com.senla.model.Role;
import com.senla.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }


    @Override
    public User getByLogin(String login) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(getClazz());
        Root<User> root = query.from(getClazz());

        query.select(root);
        query.where(builder.equal(root.get("login"), login));

        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public User loadUserByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(getClazz());
        Root<User> root = query.from(getClazz());

        query.select(root);
        query.where(builder.equal(root.get("username"), username));

        return entityManager.createQuery(query).getSingleResult();
    }
}
