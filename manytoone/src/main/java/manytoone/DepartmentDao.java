package manytoone;

import javax.persistence.*;
import java.util.List;

public class DepartmentDao {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    // CREATE
    public void saveDepartment(Department dept) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(dept);
        et.commit();
    }

    // READ BY ID
    public Department findDepartment(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Department.class, id);
    }

    // READ ALL
    public List<Department> findAllDepartments() {

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select d from Department d");
        return q.getResultList();
    }

    // UPDATE
    public void updateDepartment(Department dept) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(dept);
        et.commit();
    }

    // DELETE
    public void deleteDepartment(int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        Department d = em.find(Department.class, id);

        if (d != null) {
            et.begin();
            em.remove(d);
            et.commit();
        }
    }
}
