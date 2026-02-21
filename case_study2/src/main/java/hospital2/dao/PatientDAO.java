package hospital2.dao;

import javax.persistence.EntityManager;
import hospital2.entities.Patient;

public class PatientDAO {

    private EntityManager em;

    // DAO receives EntityManager from outside — not created here
    public PatientDAO(EntityManager em) {
        this.em = em;
    }

    public void savePatient(Patient patient) {
        em.persist(patient);
    }

    public Patient findPatient(int id) {
        return em.find(Patient.class, id);
    }

    public void updatePatient(Patient patient) {
        em.merge(patient);
    }

    public void deletePatient(int id) {
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            em.remove(patient);
        }
    }
}