package onetoonemapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class User {

	public static void main(String[] args) {
		// User.findbycarID();
		 User.removeengine();
		//User.insertCarAndEngine();
	}

	public static void insertCarAndEngine() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Engine e = new Engine();
		e.setId(100);
		e.setMileage(15);
		e.setCc(1400);
		e.setType("Petrol");

		Car c = new Car();
		c.setId(1);
		c.setModel("Virtus");
		c.setBrand("Volkswagen");
		c.setPrice(1500000);
		c.setYear("2025");
		c.setEngine(e);

		et.begin();
		em.persist(e);
		em.persist(c);
		et.commit();
	}

	public static void findbycarID() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Car c = em.find(Car.class, 1);
		System.out.println(c.getEngine().getCc());

	}

	public static void removeengine() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		// to remove foreign key reference
		Query updatequery = em.createNativeQuery("update car set engine_id = null where engine_id = ?1");
		updatequery.setParameter(1, 100);

		Query deletequery = em.createNativeQuery("delete from engine where id = ?1");
		deletequery.setParameter(1, 100);

		et.begin();
		updatequery.executeUpdate();
		deletequery.executeUpdate();
		et.commit();

	}
}
