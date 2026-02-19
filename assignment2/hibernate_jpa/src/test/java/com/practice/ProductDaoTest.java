package com.practice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.product.Product;
import com.product.ProductDao;

public class ProductDaoTest {

    static EntityManagerFactory emf;
    static EntityManager em;

    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    @Test
    public void insertProductTest() {

        ProductDao dao = new ProductDao();

        Product product = new Product();
        product.setId(2);
        product.setName("Pencil");
        product.setPrice(10);

        dao.addData(product);
    }

    @Test
    public void findByIdTest() {

        ProductDao dao = new ProductDao();

        Product p = dao.findById(1);

        assertNotNull(p);
    }
}
