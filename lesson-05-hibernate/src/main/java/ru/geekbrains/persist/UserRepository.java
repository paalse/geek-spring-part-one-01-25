package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        return executeForEntityManager(em -> em.createNamedQuery("allUsers", User.class).getResultList());
//        EntityManager em = emFactory.createEntityManager();
//        List<User> userList = em.createNamedQuery("allUsers")
//                .getResultList();
//        em.close();
//        return userList;
    }

    public User findById(long id) {
        return executeForEntityManager(em -> em.find(User.class, id));
//        EntityManager em = emFactory.createEntityManager();
//        User user = em.find(User.class, id);
//        em.close();
//        return user;
    }

    public void insert(User user) {
        executeInTransaction(em -> em.persist(user));
//        EntityManager em = emFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(user);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
    }

    public void saveOrUpdate(User user) {
        executeInTransaction(em -> em.merge(user));
//        EntityManager em = emFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.merge(user);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
    }

    public void deleteById(long id) {
        executeInTransaction(em -> {User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
        });
//        EntityManager em = emFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            User user = em.find(User.class, id);
//            if (user != null) {
//                em.remove(user);
//                em.getTransaction().commit();
//            }
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
    }





    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}