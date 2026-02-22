package crm.service;

import javax.persistence.EntityManager;
import crm.dao.ProductDAO;
import crm.entity.Product;

public class ProductService {

    private EntityManager em;
    private ProductDAO productDAO;

    public ProductService(EntityManager em) {
        this.em = em;
        this.productDAO = new ProductDAO(em);
    }

    public void addProduct(String name, double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        try {
            em.getTransaction().begin();
            productDAO.save(product);
            em.getTransaction().commit();
            System.out.println("Product added: " + product);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to add product: " + e.getMessage());
        }
    }

    public Product getProduct(Long id) {
        return productDAO.find(id);
    }
}