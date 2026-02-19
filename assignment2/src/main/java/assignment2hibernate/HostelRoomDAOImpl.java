package assignment2hibernate;

import javax.persistence.*;

public class HostelRoomDAOImpl implements HostelRoomDAO {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    @Override
    public void saveRoom(HostelRoom room) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(room);
        et.commit();

        em.close();
    }

    @Override
    public HostelRoom findRoom(int id) {

        EntityManager em = emf.createEntityManager();
        HostelRoom room = em.find(HostelRoom.class, id);
        em.close();

        return room;
    }
}
