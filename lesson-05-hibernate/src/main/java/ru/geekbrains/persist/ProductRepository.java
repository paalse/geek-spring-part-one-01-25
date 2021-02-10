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
        List<Product> ProductList = em.createNamedQuery("allProducts")
                .getResultList();
        return ProductList;
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    public void insert(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void saveOrUpdate() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void deleteById(long id) {
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
            em.getTransaction().commit();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }
}