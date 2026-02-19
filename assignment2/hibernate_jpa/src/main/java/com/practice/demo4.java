package com.practice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class demo4 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		String jpql = "select s from Student s";
		Query query = em.createQuery(jpql);
		List <Student> list = query.getResultList();
		list.forEach(System.out::println);

	
	}
}
