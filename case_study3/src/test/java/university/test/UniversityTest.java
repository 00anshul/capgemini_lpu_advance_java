package university.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import university.entities.Course;
import university.entities.Enrollment;
import university.entities.Instructor;
import university.entities.InstructorProfile;
import university.service.CourseService;
import university.service.EnrollmentService;
import university.service.InstructorService;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class UniversityTest {

    static EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("postgres");

    EntityManager em;
    InstructorService instructorService;
    CourseService courseService;
    EnrollmentService enrollmentService;

    @BeforeEach
    void setup() {
        em = emf.createEntityManager();
        instructorService = new InstructorService(em);
        courseService = new CourseService(em);
        enrollmentService = new EnrollmentService(em);
    }

    @AfterEach
    void teardown() {
        if (em.isOpen()) em.close();
    }

    // TEST 1 — OneToOne mapping test
    @Test
    @Order(1)
    void testOneToOneMapping() {
        InstructorProfile profile = new InstructorProfile();
        profile.setId(1);
        profile.setOfficeRoom("Room 101");
        profile.setPhone("9876543210");

        Instructor instructor = new Instructor();
        instructor.setId(1);
        instructor.setName("Prof. Kumar");
        instructor.setDepartment("Computer Science");

        instructorService.createInstructor(instructor, profile);

        // verify
        Instructor found = instructorService.getInstructor(1);
        assertNotNull(found);
        assertNotNull(found.getInstructorProfile());
        assertEquals("Room 101", found.getInstructorProfile().getOfficeRoom());
        System.out.println("TEST 1 PASSED — OneToOne mapping");
    }

    // TEST 2 — OneToMany mapping test
    @Test
    @Order(2)
    void testOneToManyMapping() {
        Course c1 = new Course();
        c1.setId(1);
        c1.setTitle("Java Programming");
        c1.setCredits(4);

        Course c2 = new Course();
        c2.setId(2);
        c2.setTitle("Hibernate ORM");
        c2.setCredits(3);

        instructorService.addCourseToInstructor(1, c1);
        instructorService.addCourseToInstructor(1, c2);

        // verify
        Instructor found = instructorService.getInstructorWithCourses(1);
        assertNotNull(found);
        assertEquals(2, found.getCourses().size());
        System.out.println("TEST 2 PASSED — OneToMany mapping");
    }

    // TEST 3 — ManyToOne mapping test
    @Test
    @Order(3)
    void testManyToOneMapping() {
        Enrollment e1 = new Enrollment();
        e1.setId(1);
        e1.setSemester("Spring 2026");
        e1.setGrade("A");

        Enrollment e2 = new Enrollment();
        e2.setId(2);
        e2.setSemester("Spring 2026");
        e2.setGrade("B");

        // both enrollments linked to same course
        Course course = courseService.getCourse(1);
        enrollmentService.addEnrollment(e1, course);
        enrollmentService.addEnrollment(e2, course);

        // verify course is linked
        Enrollment found = em.find(Enrollment.class, 1);
        assertNotNull(found.getCourse());
        assertEquals("Java Programming", found.getCourse().getTitle());
        System.out.println("TEST 3 PASSED — ManyToOne mapping");
    }

    // TEST 4 — DAO update test
    @Test
    @Order(4)
    void testDAOUpdate() {
        // update instructor department
        Instructor instructor = instructorService.getInstructor(1);
        instructor.setDepartment("Information Technology");
        instructorService.updateInstructor(instructor);

        Instructor updated = instructorService.getInstructor(1);
        assertEquals("Information Technology", updated.getDepartment());

        // update enrollment grade
        enrollmentService.updateGrade(1, "A+");
        Enrollment enrollment = em.find(Enrollment.class, 1);
        assertEquals("A+", enrollment.getGrade());

        System.out.println("TEST 4 PASSED — DAO update");
    }

    // TEST 5 — DAO delete test
    @Test
    @Order(5)
    void testDAODelete() {
        // delete instructor — cascades to profile and courses
        instructorService.deleteInstructor(1);

        // verify instructor is gone
        Instructor deleted = instructorService.getInstructor(1);
        assertNull(deleted);

        // verify profile is gone too — cascade worked
        InstructorProfile profile = em.find(InstructorProfile.class, 1);
        assertNull(profile);

        System.out.println("TEST 5 PASSED — DAO delete with cascade");
    }

    // TEST 6 — Full service integration test
    @Test
    @Order(6)
    void testFullServiceIntegration() {
        // create new instructor
        InstructorProfile profile = new InstructorProfile();
        profile.setId(2);
        profile.setOfficeRoom("Room 202");
        profile.setPhone("1111111111");

        Instructor instructor = new Instructor();
        instructor.setId(2);
        instructor.setName("Prof. Sharma");
        instructor.setDepartment("Mathematics");

        instructorService.createInstructor(instructor, profile);

        // add course
        Course course = new Course();
        course.setId(3);
        course.setTitle("Data Structures");
        course.setCredits(4);
        instructorService.addCourseToInstructor(2, course);

        // add enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setId(3);
        enrollment.setSemester("Fall 2026");
        enrollment.setGrade("B+");
        enrollmentService.addEnrollment(enrollment, course);

        // update grade
        enrollmentService.updateGrade(3, "A");

        // verify full flow
        Instructor found = instructorService.getInstructorWithCourses(2);
        assertEquals(1, found.getCourses().size());
        assertEquals("Data Structures", found.getCourses().get(0).getTitle());

        Enrollment updatedEnrollment = em.find(Enrollment.class, 3);
        assertEquals("A", updatedEnrollment.getGrade());
        assertEquals("Data Structures", updatedEnrollment.getCourse().getTitle());

        System.out.println("TEST 6 PASSED — Full service integration");
    }
}