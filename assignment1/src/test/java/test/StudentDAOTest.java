package test;

import app.*;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentDAOTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private StudentDAO dao;

    @BeforeAll
    void init() {
        emf = JPAUtil.getFactory();
    }

    @BeforeEach
    void setup() {
        em = emf.createEntityManager();
        dao = new StudentDAO(em);
    }

    @AfterEach
    void close() {
        em.close();
    }

    @AfterAll
    void destroy() {
        emf.close();
    }

    @Test
    void testSaveStudent() {
        Student s = new Student(
                "Anshul",
                System.currentTimeMillis()+"@test.com",
                85
        );
        dao.saveStudent(s);
        Assertions.assertTrue(s.getId() > 0);
    }

    @Test
    void testFindAllStudents() {
        dao.saveStudent(new Student("A",
                System.currentTimeMillis()+"a@test.com", 70));

        List<Student> list = dao.findAllStudents();
        Assertions.assertFalse(list.isEmpty());
    }
}
