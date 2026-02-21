package university.service;

import javax.persistence.EntityManager;
import university.dao.EnrollmentDAO;
import university.entities.Course;
import university.entities.Enrollment;

public class EnrollmentService {

    private EntityManager em;
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentService(EntityManager em) {
        this.em = em;
        this.enrollmentDAO = new EnrollmentDAO(em);
    }

    // add enrollment linked to a course
    public void addEnrollment(Enrollment enrollment, Course course) {
        enrollment.setCourse(course);
        em.getTransaction().begin();
        enrollmentDAO.saveEnrollment(enrollment);
        em.getTransaction().commit();
        System.out.println("Enrollment added: " + enrollment);
    }

    // update grade
    public void updateGrade(int enrollmentId, String newGrade) {
        em.getTransaction().begin();
        enrollmentDAO.updateGrade(enrollmentId, newGrade);
        em.getTransaction().commit();
        System.out.println("Grade updated for enrollment: " + enrollmentId);
    }
}