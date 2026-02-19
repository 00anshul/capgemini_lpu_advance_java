package casestudy1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    @Override
    public void saveOrder(PurchaseOrder o) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(o);
        et.commit();
    }

    @Override
    public PurchaseOrder findOrder(Long id) {

        EntityManager em = emf.createEntityManager();
        return em.find(PurchaseOrder.class, id);
    }

    @Override
    public List<PurchaseOrder> findOrdersByUser(Long userId) {

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery(
                "select o from User u join u.orders o where u.id = :uid");

        q.setParameter("uid", userId);

        return q.getResultList();
    }
}
