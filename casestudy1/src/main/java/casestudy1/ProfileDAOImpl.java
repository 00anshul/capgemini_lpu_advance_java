package casestudy1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProfileDAOImpl implements ProfileDAO {
	EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");
    @Override
    public void saveProfile(Profile profile) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(profile);
        et.commit();

        em.close();
    }

    @Override
    public Profile findProfile(Long id) {

        EntityManager em = emf.createEntityManager();
        Profile profile = em.find(Profile.class, id);
        em.close();

        return profile;
    }

    @Override
    public void updateProfile(Profile profile) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(profile);
        et.commit();

        em.close();
    }

    @Override
    public void deleteProfile(Long id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        Profile profile = em.find(Profile.class, id);

        if (profile != null) {
            et.begin();
            em.remove(profile);
            et.commit();
        }

        em.close();
    }
}
