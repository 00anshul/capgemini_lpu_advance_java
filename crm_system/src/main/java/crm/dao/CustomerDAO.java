package crm.dao;

import javax.persistence.EntityManager;
import crm.entity.Address;
import crm.entity.Customer;

public class CustomerDAO {

    private EntityManager em;

    public CustomerDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }

    public void addAddress(Long customerId, Address address) {
        Customer customer = em.find(Customer.class, customerId);
        if (customer != null) {
            customer.setAddress(address);
        }
    }
}