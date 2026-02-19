package manytoone;

import javax.persistence.*;
import java.util.List;

public class EmployeeDao {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    // CREATE
    public void saveEmployee(Employee emp) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(emp);
        et.commit();
    }

    // READ BY ID
    public Employee findEmployee(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Employee.class, id);
    }

    // READ ALL
    public List<Employee> findAllEmployees() {

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select e from Employee e");
        return q.getResultList();
    }

    // UPDATE
    public void updateEmployee(Employee emp) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(emp);
        et.commit();
    }

    // DELETE
    public void deleteEmployee(int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        Employee e = em.find(Employee.class, id);

        if (e != null) {
            et.begin();
            em.remove(e);
            et.commit();
        }
    }
}
