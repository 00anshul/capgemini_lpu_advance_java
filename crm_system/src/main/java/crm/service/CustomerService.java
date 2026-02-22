package crm.service;

import javax.persistence.EntityManager;
import crm.dao.CustomerDAO;
import crm.entity.Address;
import crm.entity.Customer;

public class CustomerService {

    private EntityManager em;
    private CustomerDAO customerDAO;

    public CustomerService(EntityManager em) {
        this.em = em;
        this.customerDAO = new CustomerDAO(em);
    }

    public void registerCustomer(String name, String email, String phone) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);

        try {
            em.getTransaction().begin();
            customerDAO.save(customer);
            em.getTransaction().commit();
            System.out.println("Customer registered: " + customer);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to register customer: " + e.getMessage());
        }
    }

    public void addAddressToCustomer(Long customerId, Address address) {
        try {
            em.getTransaction().begin();
            customerDAO.addAddress(customerId, address);
            em.getTransaction().commit();
            System.out.println("Address added to customer: " + customerId);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to add address: " + e.getMessage());
        }
    }

    public Customer getCustomer(Long id) {
        return customerDAO.find(id);
    }
}