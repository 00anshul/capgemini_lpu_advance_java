package com.product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class priceUpdate {
public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();
  
    String jpql = "update product p set p.quantity=?1 where p.id=?2";
    et.begin();
    Query query =em.createQuery(jpql);
    
    query.setParameter(1, 10);
    query.setParameter(2, 2);
    
    query.executeUpdate();
    et.commit();
    em.close();
    emf.close();
    
}
}
