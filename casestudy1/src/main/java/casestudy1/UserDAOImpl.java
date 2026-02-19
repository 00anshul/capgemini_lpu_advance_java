package casestudy1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserDAOImpl implements UserDAO {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    @Override
    public void saveUser(User u) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(u);
        et.commit();
    }

    @Override
    public User findUser(Long id) {

        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public void updateUser(User u) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(u);
        et.commit();
    }

    @Override
    public void deleteUser(Long id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        User u = em.find(User.class, id);

        if (u != null) {
            et.begin();
            em.remove(u);
            et.commit();
        }
    }
}
