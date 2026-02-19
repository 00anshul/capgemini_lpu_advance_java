package assignment2hibernate;

import javax.persistence.*;

public class AadharCardDAOImpl implements AadharCardDAO {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    @Override
    public void saveAadhar(AadharCard aadhar) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(aadhar);
        et.commit();

        em.close();
    }

    @Override
    public AadharCard findAadhar(int id) {

        EntityManager em = emf.createEntityManager();
        AadharCard aadhar = em.find(AadharCard.class, id);
        em.close();

        return aadhar;
    }
}
