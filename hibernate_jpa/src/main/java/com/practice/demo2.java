package com.practice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class demo2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();
		Student student = em.find(Student.class, 1);
		et.begin();
		em.remove(student);
		et.commit();
	
	}
}
