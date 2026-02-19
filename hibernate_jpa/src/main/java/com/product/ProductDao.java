package com.product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProductDao {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    EntityManager em = emf.createEntityManager();

    public void addData(Product p) {

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(p);
        et.commit();
    }

    public Product findById(int id) {
        return em.find(Product.class, id);
    }

    public String removeData(int id) {

        EntityTransaction et = em.getTransaction();

        Product product = em.find(Product.class, id);

        if (product != null) {
            et.begin();
            em.remove(product);
            et.commit();
            return "product created";
        }
        else {
        	return "not there";
        }
    }
}
