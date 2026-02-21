package university.dao;

import javax.persistence.EntityManager;
import university.entities.Instructor;

public class InstructorDAO {

    private EntityManager em;

    public InstructorDAO(EntityManager em) {
        this.em = em;
    }

    public void saveInstructor(Instructor instructor) {
        em.persist(instructor);
    }

    public Instructor findInstructor(int id) {
        return em.find(Instructor.class, id);
    }

    public void updateInstructor(Instructor instructor) {
        em.merge(instructor);
    }
}