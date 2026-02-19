package onetomanybid;

import javax.persistence.*;
import java.util.List;

public class CollegeDao {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    // CREATE
    public void saveCollege(College college) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(college);
        et.commit();
    }

    // READ BY ID
    public College findCollege(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(College.class, id);
    }

    // READ ALL
    public List<College> findAllColleges() {

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select c from College c");
        return q.getResultList();
    }

    // UPDATE
    public void updateCollege(College college) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(college);
        et.commit();
    }

    // DELETE
    public void deleteCollege(int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        College c = em.find(College.class, id);

        if (c != null) {
            et.begin();
            em.remove(c);
            et.commit();
        }
    }
}
