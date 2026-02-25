package com.capgemini.main;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

@Component
public class JpaUtil {

	private EntityManagerFactory emf;
	private EntityManager em;

	public EntityManagerFactory getEmf() {
		return emf;
	}


	public EntityManager getEm() {
		return em;
	}



	@PostConstruct
	public void createConnection() {
		emf = Persistence.createEntityManagerFactory("postgres");
		em = emf.createEntityManager();
		System.out.println("Your bluetooth device is connected successfully");
	}

	@PreDestroy
	public void print() {
		em.close();
		emf.close();
		System.out.println("power off");
	}
}
