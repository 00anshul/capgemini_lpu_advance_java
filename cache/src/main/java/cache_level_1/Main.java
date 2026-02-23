package cache_level_1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
public static void main(String[] args) {
	//insert();
	read();
	
}
public static void insert() {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	Product p1 = new Product();
	p1.setId(1);
	p1.setName("Teddy");
	
	Product p2 = new Product();
	p2.setId(2);
	p2.setName("Rabbit");
	
	et.begin();
	em.persist(p1);
	em.persist(p2);
	et.commit();
	
}
public static void read() {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	EntityManager em1 = emf.createEntityManager();
	EntityTransaction et = em1.getTransaction();
	
	System.out.println("first cache");
	Product p1 = em1.find(Product.class, 1);
	System.out.println(p1.getName());
	
	em1.close();
	
	EntityManager em2 = emf.createEntityManager();
	System.out.println("second cache");
	Product p2 = em2.find(Product.class, 1);
	System.out.println(p2.getName());
	
	em2.close();
}


}
