package crm.service;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import crm.dao.CustomerDAO;
import crm.dao.OrderDAO;
import crm.dao.ProductDAO;
import crm.entity.Customer;
import crm.entity.Order;
import crm.entity.Product;

public class OrderService {

    private EntityManager em;
    private OrderDAO orderDAO;
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;

    public OrderService(EntityManager em) {
        this.em = em;
        this.orderDAO = new OrderDAO(em);
        this.customerDAO = new CustomerDAO(em);
        this.productDAO = new ProductDAO(em);
    }

    public void placeOrder(Long customerId, List<Long> productIds) {
        try {
            em.getTransaction().begin();

            Customer customer = customerDAO.find(customerId);
            if (customer == null) {
                System.out.println("Customer not found: " + customerId);
                em.getTransaction().rollback();
                return;
            }

            Order order = new Order();
            order.setOrderDate(LocalDate.now());
            order.setCustomer(customer);

            // calculate total and add products
            double total = 0;
            for (Long productId : productIds) {
                Product product = productDAO.find(productId);
                if (product != null) {
                    order.getProducts().add(product);
                    total += product.getPrice();
                }
            }

            order.setTotalAmount(total);
            customer.getOrders().add(order);
            orderDAO.save(order);

            em.getTransaction().commit();
            System.out.println("Order placed: " + order);
            System.out.println("Total amount: " + total);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to place order: " + e.getMessage());
        }
    }

    public Order getOrder(Long id) {
        return orderDAO.find(id);
    }
}