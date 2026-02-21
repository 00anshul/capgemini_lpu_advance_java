package manytomanybi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

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
		st.setId(1);
		st.setName("Anshul");
		st.setBranch("CSE");
		st.setGender("Male");

		// Linking subjects to student
		List<Subject> subjectList = new ArrayList<>();
		subjectList.add(s1);
		subjectList.add(s2);
		st.setSubjects(subjectList);

		// ---------- Persist ----------
		et.begin();
		em.persist(s1);
		em.persist(s2);
		em.persist(st);
		et.commit();

		s1.getStudents().add(st); // subject → student (inverse, for in-memory use)
		s2.getStudents().add(st); // subject → student (inverse, for in-memory use)
		System.out.println("Saved successfully");

//        // ---------- Delete Subject ----------
//        em.getTransaction().begin();
//
//        Subject subject = em.find(Subject.class, 1);
//        if (subject != null) {
//            // Remove from student's list first (owning side must be updated)
//            List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class)
//                                       .getResultList();
//            for (Student s : students) {
//                s.getSubjects().remove(subject);  // removes from junction table
//            }
//            em.remove(subject);  // then delete subject itself
//            System.out.println("Subject deleted");
//        }
//
//        em.getTransaction().commit();
		em.close();
		emf.close();
	}
}