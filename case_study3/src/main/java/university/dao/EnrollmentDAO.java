package university.dao;

import javax.persistence.EntityManager;
import university.entities.Enrollment;

public class EnrollmentDAO {

    private EntityManager em;

    public EnrollmentDAO(EntityManager em) {
        this.em = em;
    }

    public void saveEnrollment(Enrollment enrollment) {
        em.persist(enrollment);
    }

    public void updateGrade(int enrollmentId, String newGrade) {
        Enrollment enrollment = em.find(Enrollment.class, enrollmentId);
        if (enrollment != null) {
            enrollment.setGrade(newGrade);   // dirty checking handles update
        }
    }
}