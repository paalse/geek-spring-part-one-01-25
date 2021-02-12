package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository {

    private final EntityManagerFactory emFactory;


    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<User> userList = em.createNamedQuery("allUsers")
                .getResultList();
        em.close();
        return userList;
    }

    public User findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public void insert(User user) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate() {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteById(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
            em.getTransaction().commit();
        }
        em.close();
    }
}