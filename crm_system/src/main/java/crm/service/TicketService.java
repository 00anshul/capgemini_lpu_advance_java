package crm.service;

import javax.persistence.EntityManager;
import crm.dao.OrderDAO;
import crm.dao.TicketDAO;
import crm.entity.Order;
import crm.entity.SupportTicket;

public class TicketService {

    private EntityManager em;
    private TicketDAO ticketDAO;
    private OrderDAO orderDAO;

    public TicketService(EntityManager em) {
        this.em = em;
        this.ticketDAO = new TicketDAO(em);
        this.orderDAO = new OrderDAO(em);
    }

    public void raiseTicket(Long orderId, String issueDescription) {
        try {
            em.getTransaction().begin();

            Order order = orderDAO.find(orderId);
            if (order == null) {
                System.out.println("Order not found: " + orderId);
                em.getTransaction().rollback();
                return;
            }

            SupportTicket ticket = new SupportTicket();
            ticket.setIssueDescription(issueDescription);
            ticket.setStatus("OPEN");
            ticket.setOrder(order);

            ticketDAO.save(ticket);
            em.getTransaction().commit();
            System.out.println("Support ticket raised: " + ticket);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to raise ticket: " + e.getMessage());
        }
    }
}