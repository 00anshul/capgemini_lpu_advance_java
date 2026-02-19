package app;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentDAO {

    private EntityManager em;

    public StudentDAO(EntityManager em) {
        this.em = em;
    }

    public void saveStudent(Student s) {
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }

    public Student findStudentById(int id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAllStudents() {
        return em.createQuery("FROM Student", Student.class)
                .getResultList();
    }

    public void updateStudent(Student s) {
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
    }

    public void deleteStudent(int id) {
        em.getTransaction().begin();
        Student s = em.find(Student.class, id);
        if (s != null) em.remove(s);
        em.getTransaction().commit();
    }
}
