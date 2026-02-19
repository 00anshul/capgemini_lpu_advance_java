package assignment2hibernate;

import javax.persistence.*;

public class StudentDAOImpl implements StudentDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

	@Override
	public void saveStudent(Student student) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(student);
		et.commit();

		em.close();
	}

	@Override
	public Student findStudent(int id) {

		EntityManager em = emf.createEntityManager();
		Student student = em.find(Student.class, id);
		em.close();

		return student;
	}

	@Override
	public void deleteStudent(int id) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Student student = em.find(Student.class, id);

		if (student != null) {
			et.begin();
			em.remove(student);
			et.commit();
		}

		em.close();
	}
}
