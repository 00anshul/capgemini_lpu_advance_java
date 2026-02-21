package hospital2.dao;

import javax.persistence.EntityManager;
import hospital2.entities.Doctor;

public class DoctorDAO {

    private EntityManager em;

    public DoctorDAO(EntityManager em) {
        this.em = em;
    }

    public void saveDoctor(Doctor doctor) {
        em.persist(doctor);
    }

    public Doctor findDoctor(int id) {
        return em.find(Doctor.class, id);
    }
}