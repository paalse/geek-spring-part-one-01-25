package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductRepository {

    private final EntityManagerFactory emFactory;
    private EntityManager em;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
        this.em = emFactory.createEntityManager();
    }

    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> ProductList = em.createNamedQuery("allProducts")
                .getResultList();
        em.close();
        return ProductList;
    }

    public Product findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    public void insert(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void deleteById(long id) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}