package mockito;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	EntityManager em = emf.createEntityManager();
	
	
	public void insertUsers() {
		EntityTransaction et = em.getTransaction();
		User user = new User();
		user.setId(1);
		user.setName("Ram");
		user.setBalance(10000);
		
		et.begin();
		em.persist(user);
		et.commit();
	}
	public User findbyId(int id) {
		return em.find(User.class, 1);
	}
}
