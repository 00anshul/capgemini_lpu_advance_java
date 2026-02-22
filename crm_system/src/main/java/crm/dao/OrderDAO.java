package crm.dao;

import javax.persistence.EntityManager;
import crm.entity.Order;

public class OrderDAO {

    private EntityManager em;

    public OrderDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order find(Long id) {
        return em.find(Order.class, id);
    }
}