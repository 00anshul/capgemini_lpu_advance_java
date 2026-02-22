package crm.dao;

import javax.persistence.EntityManager;
import crm.entity.Product;

public class ProductDAO {

    private EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Product product) {
        em.persist(product);
    }

    public Product find(Long id) {
        return em.find(Product.class, id);
    }
}