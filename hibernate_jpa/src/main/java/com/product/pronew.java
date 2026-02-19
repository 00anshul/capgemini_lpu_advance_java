package com.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class pronew {
public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();
    
//    String jpql = "select p from Product p where p.price>?1";
//    
//    Query query =em.createQuery(jpql);
//    query.setParameter(1, 100.0);
//    
//    List<Product>list  = query.getResultList();
//    list.forEach(i->System.out.println(i.getName()));
//    
    String jpql = "select p from Product p where p.price>=:a and p.quantity>=:b";
    Query query =em.createQuery(jpql);
    query.setParameter("a", 10.0);
    query.setParameter("b", 0);

    List<Product>list  = query.getResultList();
    for(Product p : list) {
    	System.out.println(p);
    }


} 
}
