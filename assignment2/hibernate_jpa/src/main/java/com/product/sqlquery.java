package com.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class sqlquery {
public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();
  
    String sql= "select * from Product";
    Query query  = em .createNativeQuery(sql);
    List<Product> list = query.getResultList();
    System.out.println(list);
}
}
