package casestudy1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PaymentDAOImpl implements PaymentDAO {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    @Override
    public void savePayment(Payment p) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(p);
        et.commit();
    }

    @Override
    public Payment findPayment(Long id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Payment.class, id);
    }
}
