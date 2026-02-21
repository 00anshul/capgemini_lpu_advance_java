package university.service;

import java.util.List;
import javax.persistence.EntityManager;
import university.dao.CourseDAO;
import university.entities.Course;

public class CourseService {

    private EntityManager em;
    private CourseDAO courseDAO;

    public CourseService(EntityManager em) {
        this.em = em;
        this.courseDAO = new CourseDAO(em);
    }

    public Course getCourse(int id) {
        return courseDAO.findCourse(id);
    }

    public List<Course> getCoursesByInstructor(int instructorId) {
        List<Course> courses = courseDAO.findByInstructor(instructorId);
        System.out.println("Courses for instructor id " + instructorId + ":");
        for (Course c : courses) {
            System.out.println("  " + c);
        }
        return courses;
    }
}