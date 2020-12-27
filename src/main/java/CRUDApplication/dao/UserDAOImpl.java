package CRUDApplication.dao;

import CRUDApplication.models.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import CRUDApplication.models.Role;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserByName(String login) {
        return entityManager.createQuery("from User where login = :login", User.class).setParameter("login", login).getSingleResult();
    }

    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery("from Role where roleName = :role", Role.class).setParameter("role", role).getSingleResult();
    }
}
