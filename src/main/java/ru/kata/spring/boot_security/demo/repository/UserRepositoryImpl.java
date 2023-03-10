package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;
    public UserRepositoryImpl(EntityManager entityManager) {
          this.entityManager = entityManager;
    }

    @Override
    public User findUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String username) {
        return entityManager.createQuery("from User where username = :username", User.class)
                    .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void update(Long id, User user) {
        for (Role role : user.getRoles()) {
            entityManager.merge(role);
        }
        entityManager.merge(user);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = findUser(id);
        entityManager.remove(findUser(id));
    }

}
