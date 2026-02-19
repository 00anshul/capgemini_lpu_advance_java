package manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("postgres");

        EntityManager em = emf.createEntityManager();

        // ---------- Creating Subjects ----------
        Subject s1 = new Subject();
        s1.setId(1);
        s1.setName("Java");
        s1.setNumofdays(30);

        Subject s2 = new Subject();
        s2.setId(2);
        s2.setName("Hibernate");
        s2.setNumofdays(20);

        // ---------- Creating Student ----------
        Student st = new Student();
        st.setName("Anshul");
        st.setBranch("CSE");
        st.setGender("Male");

        // Adding subjects to student
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(s1);
        subjectList.add(s2);

        st.setSubjects(subjectList);

        // ---------- Persist Data ----------
        em.getTransaction().begin();

        em.persist(s1);
        em.persist(s2);
        em.persist(st);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Data inserted successfully");
        
        em.getTransaction().begin();
        
     // Find subject
        Subject subject = em.find(Subject.class, 1);

        if (subject != null) {

            // Remove subject from all students
            List<Student> students =
                    em.createQuery("SELECT s FROM Student s", Student.class)
                      .getResultList();

            for (Student st1 : students) {
                if (st1.getSubjects().contains(subject)) {
                    st1.getSubjects().remove(subject);
                }
            }

            // Now delete subject
            em.remove(subject);

            System.out.println("Subject deleted successfully");
        } else {
            System.out.println("Subject not found");
        }

        em.getTransaction().commit();
    }
}
