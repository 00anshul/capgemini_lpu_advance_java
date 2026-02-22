package crm.service;

import java.util.List;
import javax.persistence.EntityManager;
import crm.entity.Lead;
import crm.entity.SalesEmployee;

public class ReportService {

    private EntityManager em;

    public ReportService(EntityManager em) {
        this.em = em;
    }

    public void getEmployeePerformance(Long employeeId) {
        SalesEmployee employee = em.find(SalesEmployee.class, employeeId);
        if (employee == null) {
            System.out.println("Employee not found: " + employeeId);
            return;
        }

        // total leads assigned
        List<Lead> allLeads = em.createQuery(
            "SELECT l FROM Lead l WHERE l.employee.id = :eid", Lead.class)
            .setParameter("eid", employeeId)
            .getResultList();

        // converted leads
        List<Lead> converted = em.createQuery(
            "SELECT l FROM Lead l WHERE l.employee.id = :eid AND l.status = :s",
             Lead.class)
            .setParameter("eid", employeeId)
            .setParameter("s", "CONVERTED")
            .getResultList();

        System.out.println("=== Employee Performance Report ===");
        System.out.println("Employee: " + employee.getName());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("Total Leads Assigned: " + allLeads.size());
        System.out.println("Leads Converted: " + converted.size());
        System.out.println("Conversion Rate: " + 
            (allLeads.size() > 0 ? 
            (converted.size() * 100 / allLeads.size()) + "%" : "N/A"));
    }
}