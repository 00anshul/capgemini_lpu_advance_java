package hospital.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hospital.entities.MedicalRecord;
import hospital.entities.Patient;

public class CRUDTest1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalERP");
        EntityManager em = emf.createEntityManager();

        // CREATE

        MedicalRecord mr = new MedicalRecord();
        mr.setId(1);
        mr.setDiagnosis("Hypertension");
        mr.setTreatment("Beta Blockers");
        mr.setNotes("Monitor BP weekly");

        Patient p = new Patient();
        p.setId(1);
        p.setName("Anshul");
        p.setAge(24);
        p.setGender("Male");
        p.setMedicalRecord(mr);   // linking medical record to patient

        em.getTransaction().begin();
        em.persist(p);            // cascades to mr automatically, no need to persist mr separately
        em.getTransaction().commit();
        System.out.println("SAVED: " + p);


        // READ

        Patient found = em.find(Patient.class, 1);
        System.out.println("FOUND: " + found);
        System.out.println("MEDICAL RECORD: " + found.getMedicalRecord());


        // UPDATE

        em.getTransaction().begin();
        found.setAge(25);                                      // update patient
        found.getMedicalRecord().setDiagnosis("Diabetes");     // update medical record
        em.getTransaction().commit();
        System.out.println("UPDATED: " + found);

 
        // DELETE

        em.getTransaction().begin();
        Patient toDelete = em.find(Patient.class, 1);
        em.remove(toDelete);   // cascades — deletes medical record too
        em.getTransaction().commit();
        System.out.println("DELETED patient and medical record");

        em.close();
        emf.close();
    }
}
