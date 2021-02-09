package ru.geekbrains.persist;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        return null;
    }

    public User findById(long id) {
        return null;
    }

    public void insert(User user) {
    }

    public void update(User user) {
    }

    public void delete(long id) {
    }

}