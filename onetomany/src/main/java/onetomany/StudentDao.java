package onetomany;

import javax.persistence.*;
import java.util.List;

public class StudentDao {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    // CREATE
    public void saveStudent(Student student) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(student);
        et.commit();
    }

    // READ BY ID
    public Student findStudent(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Student.class, id);
    }

    // READ ALL
    public List<Student> findAllStudents() {

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select s from Student s");
        return q.getResultList();
    }

    // UPDATE
    public void updateStudent(Student student) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(student);
        et.commit();
    }

    // DELETE (important for OneToMany)
    public void deleteStudent(int studentId, int collegeId) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        Student s = em.find(Student.class, studentId);
        College c = em.find(College.class, collegeId);

        if (s != null && c != null) {

            et.begin();

            // break relationship
            c.getStudent().remove(s);

            // delete student
            em.remove(s);

            et.commit();
        }
    }
}
