package onetoonebidirect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
public static void main(String[] args) {
	 EntityManagerFactory emf =
             Persistence.createEntityManagerFactory("postgres");

     EntityManager em = emf.createEntityManager();
     EntityTransaction et = em.getTransaction();
     
     Passport p = new Passport();
     p.setId(101);
     p.setName("Miller");
     p.setDob("24/4/2001");
     
     Person person = new Person();
     p.setPerson(person);
     
     person .setId(1);
     person.setName("Allen");
     person.setPassport(p);
     et.begin();
     em.persist(person);
     et.commit();
}
}
