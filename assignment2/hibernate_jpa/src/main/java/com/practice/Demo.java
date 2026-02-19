package com.practice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Demo {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();
		Student s = new Student();
		s.setId(2);
		s.setName("Rohit");
		s.setPercentage(79);
		et.begin();
		em.persist(s);
		et.commit();
		emf.close();
	}
}
