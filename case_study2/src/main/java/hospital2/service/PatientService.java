package hospital2.service;

import javax.persistence.EntityManager;
import hospital2.dao.PatientDAO;
import hospital2.entities.MedicalRecord;
import hospital2.entities.Patient;

public class PatientService {

    private EntityManager em;
    private PatientDAO patientDAO;

    public PatientService(EntityManager em) {
        this.em = em;
        this.patientDAO = new PatientDAO(em);   // same em passed to DAO
    }

    // register patient WITH medical record in one transaction
    public void registerPatient(Patient patient, MedicalRecord medicalRecord) {
        patient.setMedicalRecord(medicalRecord);
        em.getTransaction().begin();
        patientDAO.savePatient(patient);         // cascades to medicalRecord
        em.getTransaction().commit();
        System.out.println("Patient registered: " + patient);
    }

    public Patient getPatient(int id) {
        return patientDAO.findPatient(id);
    }

    public void updatePatient(Patient patient) {
        em.getTransaction().begin();
        patientDAO.updatePatient(patient);
        em.getTransaction().commit();
        System.out.println("Patient updated: " + patient);
    }

    public void deletePatient(int id) {
        em.getTransaction().begin();
        patientDAO.deletePatient(id);
        em.getTransaction().commit();
        System.out.println("Patient deleted with id: " + id);
    }
}