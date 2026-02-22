package crm.service;

import javax.persistence.EntityManager;
import crm.dao.CustomerDAO;
import crm.dao.LeadDAO;
import crm.entity.Customer;
import crm.entity.Lead;
import crm.entity.SalesEmployee;

public class LeadService {

    private EntityManager em;
    private LeadDAO leadDAO;
    private CustomerDAO customerDAO;

    public LeadService(EntityManager em) {
        this.em = em;
        this.leadDAO = new LeadDAO(em);
        this.customerDAO = new CustomerDAO(em);
    }

    public void createLead(String name, String source, String contactInfo) {
        Lead lead = new Lead();
        lead.setName(name);
        lead.setSource(source);
        lead.setContactInfo(contactInfo);
        lead.setStatus("NEW");

        try {
            em.getTransaction().begin();
            leadDAO.save(lead);
            em.getTransaction().commit();
            System.out.println("Lead created: " + lead);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to create lead: " + e.getMessage());
        }
    }

    public void assignLeadToEmployee(Long leadId, Long employeeId) {
        try {
            em.getTransaction().begin();
            Lead lead = leadDAO.find(leadId);
            SalesEmployee employee = leadDAO.findEmployee(employeeId);

            if (lead == null) {
                System.out.println("Lead not found: " + leadId);
                em.getTransaction().rollback();
                return;
            }
            if (employee == null) {
                System.out.println("Employee not found: " + employeeId);
                em.getTransaction().rollback();
                return;
            }

            lead.setEmployee(employee);
            lead.setStatus("ASSIGNED");
            employee.getLeads().add(lead);

            em.getTransaction().commit();
            System.out.println("Lead " + leadId + " assigned to " + employee.getName());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to assign lead: " + e.getMessage());
        }
    }

    // converts lead into a customer — key business logic
    public void convertLeadToCustomer(Long leadId) {
        try {
            em.getTransaction().begin();
            Lead lead = leadDAO.find(leadId);

            if (lead == null) {
                System.out.println("Lead not found: " + leadId);
                em.getTransaction().rollback();
                return;
            }

            // create customer from lead data
            Customer customer = new Customer();
            customer.setName(lead.getName());
            customer.setEmail(lead.getContactInfo());
            customer.setPhone("N/A");

            customerDAO.save(customer);

            // link customer to lead and update status
            lead.setCustomer(customer);
            lead.setStatus("CONVERTED");

            em.getTransaction().commit();
            System.out.println("Lead converted to customer: " + customer);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to convert lead: " + e.getMessage());
        }
    }

    public void createEmployee(String name, String department) {
        SalesEmployee employee = new SalesEmployee();
        employee.setName(name);
        employee.setDepartment(department);

        try {
            em.getTransaction().begin();
            leadDAO.saveEmployee(employee);
            em.getTransaction().commit();
            System.out.println("Employee created: " + employee);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to create employee: " + e.getMessage());
        }
    }
}