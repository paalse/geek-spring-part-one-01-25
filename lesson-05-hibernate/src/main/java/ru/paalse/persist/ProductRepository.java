package ru.paalse.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class ProductRepository {

    private final EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Product> findAll() {
        return executeForEntityManager(em -> em.createNamedQuery("allProducts", Product.class).getResultList());
//        EntityManager em = emFactory.createEntityManager();
//        List<Product> productList = em.createNamedQuery("allProducts")
//                .getResultList();
//        em.close();
//        return productList;
    }

    public Product findById(long id) {
        return executeForEntityManager(em -> em.find(Product.class, id));
//        EntityManager em = emFactory.createEntityManager();
//        Product product = em.find(Product.class, id);
//        em.close();
//        return product;
    }

    public void insert(Product product) {
        executeInTransaction(em -> em.persist(product));
//        EntityManager em = emFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(product);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
    }

    public void saveOrUpdate(Product product) {
        executeInTransaction(em -> em.merge(product));
//        EntityManager em = emFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.merge(product);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
    }

    public void deleteById(long id) {
        executeInTransaction(em -> {Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
        });
//        EntityManager em = emFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            Product product = em.find(Product.class, id);
//            if (product != null) {
//                em.remove(product);
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
