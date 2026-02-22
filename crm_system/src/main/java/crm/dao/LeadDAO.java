package crm.dao;

import javax.persistence.EntityManager;
import crm.entity.Lead;
import crm.entity.SalesEmployee;

public class LeadDAO {

    private EntityManager em;

    public LeadDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Lead lead) {
        em.persist(lead);
    }

    public Lead find(Long id) {
        return em.find(Lead.class, id);
    }

    public SalesEmployee findEmployee(Long id) {
        return em.find(SalesEmployee.class, id);
    }

    public void saveEmployee(SalesEmployee employee) {
        em.persist(employee);
    }
}