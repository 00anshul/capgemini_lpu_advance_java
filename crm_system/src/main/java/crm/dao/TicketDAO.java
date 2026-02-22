package crm.dao;

import javax.persistence.EntityManager;
import crm.entity.SupportTicket;

public class TicketDAO {

    private EntityManager em;

    public TicketDAO(EntityManager em) {
        this.em = em;
    }

    public void save(SupportTicket ticket) {
        em.persist(ticket);
    }

    public SupportTicket find(Long id) {
        return em.find(SupportTicket.class, id);
    }
}