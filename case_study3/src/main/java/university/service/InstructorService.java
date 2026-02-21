package university.service;

import java.util.List;
import university.entities.Enrollment;
import javax.persistence.EntityManager;
import university.dao.CourseDAO;
import university.dao.InstructorDAO;
import university.entities.Course;
import university.entities.Instructor;
import university.entities.InstructorProfile;

public class InstructorService {

    private EntityManager em;
    private InstructorDAO instructorDAO;
    private CourseDAO courseDAO;

    public InstructorService(EntityManager em) {
        this.em = em;
        this.instructorDAO = new InstructorDAO(em);
        this.courseDAO = new CourseDAO(em);
    }

    // create instructor with profile in one transaction
    public void createInstructor(Instructor instructor, InstructorProfile profile) {
        instructor.setInstructorProfile(profile);
        em.getTransaction().begin();
        instructorDAO.saveInstructor(instructor);   // cascades to profile
        em.getTransaction().commit();
        System.out.println("Instructor created: " + instructor);
    }

    public Instructor getInstructor(int id) {
        return instructorDAO.findInstructor(id);
    }

    // add course to instructor
    public void addCourseToInstructor(int instructorId, Course course) {
        em.getTransaction().begin();
        Instructor instructor = instructorDAO.findInstructor(instructorId);
        instructor.getCourses().add(course);
        courseDAO.saveCourse(course);
        em.getTransaction().commit();
        System.out.println("Course added: " + course);
    }

    // fetch instructor with all courses
    public Instructor getInstructorWithCourses(int instructorId) {
        Instructor instructor = instructorDAO.findInstructor(instructorId);
        System.out.println("Instructor: " + instructor);
        System.out.println("Courses:");
        for (Course c : instructor.getCourses()) {
            System.out.println("  " + c);
        }
        return instructor;
    }

    // update instructor department
    public void updateInstructor(Instructor instructor) {
        em.getTransaction().begin();
        instructorDAO.updateInstructor(instructor);
        em.getTransaction().commit();
        System.out.println("Instructor updated: " + instructor);
    }

    // delete instructor — cascades to profile and courses
    public void deleteInstructor(int id) {
        em.getTransaction().begin();
        Instructor instructor = instructorDAO.findInstructor(id);
        if (instructor != null) {
            // first null out course reference in all enrollments
            for (Course course : instructor.getCourses()) {
                List<Enrollment> enrollments = em
                    .createQuery(
                        "SELECT e FROM Enrollment e WHERE e.course.id = :cid",
                         Enrollment.class)
                    .setParameter("cid", course.getId())
                    .getResultList();

                for (Enrollment e : enrollments) {
                    e.setCourse(null);   // remove FK reference first
                }
            }
            em.remove(instructor);   // now safe — cascades to profile and courses
        }
        em.getTransaction().commit();
        System.out.println("Instructor deleted with id: " + id);
    }
    }
