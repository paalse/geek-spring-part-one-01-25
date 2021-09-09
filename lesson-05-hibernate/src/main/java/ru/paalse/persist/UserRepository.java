package ru.paalse.persist;

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
        User user = em.createQuery("from User u where u.id =:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
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

    public void update(User user) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
